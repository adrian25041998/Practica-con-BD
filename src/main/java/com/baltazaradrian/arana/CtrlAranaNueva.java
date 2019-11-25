/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltazaradrian.arana;

import com.baltazaradrian.web.Mensajes;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Roberto MuVe
 */
/**
 * Controlador que se utiliza en varias vistas. La anotación
 * <code>@ViewScoped</code> indica que los objetos se mantienen almacenados en
 * el archivo de sesión mientras se muestre la vista que está usando este bean.
 * Al cambiar de vista, los datos se pierden.
 */
@Named
@ViewScoped
public class CtrlAranaNueva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Mensajes mensajes;
    @Inject
    private DaoArana dao;
    private Arana modelo;

    @PostConstruct
    void init() {
        modelo = new Arana();
    }

    public Arana getModelo() {
        return modelo;
    }

    public String guarda() {
        try {
            dao.agrega(modelo);
            return "index?faces-redirect=true";
        } catch (Exception ex) {
            mensajes.procesa(ex);
            return null;
        }
    }
}
