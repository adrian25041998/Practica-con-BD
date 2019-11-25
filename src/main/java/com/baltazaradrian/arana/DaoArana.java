/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltazaradrian.arana;

import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Roberto MuVe
 */
@Stateless
@Dependent
public class DaoArana {

    @Inject
    private EntityManager em;

    public List<Arana> consulta() {
        /* Con el resultado de la consulta se llena una lista con objetos de la
     * clase "Pasatiempo". */
        return em.createQuery("SELECT c FROM Arana c ORDER BY c.nombre",
                Arana.class).getResultList();
    }

    public Arana busca(Integer id) {
        return em.find(Arana.class, id);
    }

    public void agrega(Arana modelo) {
        em.persist(modelo); // Agrega el modelo a la base de datos.
    }

    public void modifica(Arana modelo) {
        em.merge(modelo);// Guarda los cambios al modelo.
    }

    public void elimina(Arana modelo) {
        // Busca el modelo en base a su id.
        final Arana anterior = em.find(Arana.class, modelo.getId());
        // Si el resultado es null, el chismoso ya no está registrado.
        if (anterior != null) {
            // Pero si la referencia es diferente de null, hay que eliminar el objeto.
            em.remove(anterior);
        }
    }
}
