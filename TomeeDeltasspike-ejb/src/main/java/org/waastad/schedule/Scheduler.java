/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.schedule;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.waastad.entity.DeltaCustomer;
import org.waastad.repository.CustomerRepository;

/**
 *
 * @author Helge Waastad <helge@waastad.org>
 */
@Singleton
public class Scheduler {
    
    private static final Logger LOG = LoggerFactory.getLogger(Scheduler.class);
    
    @Inject
    private CustomerRepository customerRepository;
    
    @Schedule(hour = "*", minute = "*", second = "*/10")
    public void doStuff() {
        LOG.info("Found {} customers", customerRepository.count());
        DeltaCustomer c = customerRepository.findBy(2L);
        if (c == null) {
            LOG.info("No customer 2 found");
        } else {
            LOG.info("Found customer {} by id 2", c.getName());
        }
    }
}
