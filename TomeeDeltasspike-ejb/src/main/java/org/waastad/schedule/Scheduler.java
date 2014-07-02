/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.schedule;

import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.waastad.entity.DeltaCustomer;
import org.waastad.jms.EventMessage;
import org.waastad.jms.JmsService;
import org.waastad.qualifier.JmsSession;
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

    @Inject
    private JmsService jmsService;

    @Schedule(hour = "*", minute = "*", second = "*/30")
    public void doStuff() {
//        LOG.info("Found {} customers, eventlog size: {}", customerRepository.count());
        jmsService.sendEvent(new EventMessage("doing stuff...."));
//        List<DeltaCustomer> c = customerRepository.findAll();
        
//        for (DeltaCustomer k : c) {
//            LOG.info("Customer {} has {} users", k.getName(), k.getUserCollection().size());
//        }
    }
}
