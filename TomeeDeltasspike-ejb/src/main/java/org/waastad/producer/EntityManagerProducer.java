/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.waastad.producer;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Helge Waastad <helge@waastad.org>
 */
public class EntityManagerProducer {
    
    @PersistenceContext(unitName = "DeltaPU")
    private EntityManager em;

    @Produces
    @Default
    public EntityManager create() {
        System.out.println("Producing entitymanager.....");
        return em;
    }

    public void close(@Disposes @Default EntityManager em) {
        System.out.println("Disposing entitymanager.....");
        if (em.isOpen()) {
            em.close();
        }
    }
}
