/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Helge Waastad <helge@waastad.org>
 */
@ApplicationScoped
public class EntityManagerProducer {
    private static final Logger LOG = LoggerFactory.getLogger(EntityManagerProducer.class);

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    
//    @PersistenceContext(unitName = "DeltaPU")
//    private EntityManager em;

    @Produces
    @Default
    @RequestScoped
    public EntityManager create() {
        LOG.debug("Producing entitymanager.....");
        return this.entityManagerFactory.createEntityManager();
 
    }

    public void close(@Disposes @Default EntityManager em) {
        LOG.debug("Disposing entitymanager.....");
        if (em.isOpen()) {
            em.close();
        }
    }
    
//    @Produces
//    @Default
//    public EntityManager create() {
//        LOG.info("Producing entitymanager.....");
//        return em;
//    }
//
//    public void close(@Disposes @Default EntityManager em) {
//        LOG.info("Disposing entitymanager.....");
//        if (em.isOpen()) {
//            em.close();
//        }
//    }
}
