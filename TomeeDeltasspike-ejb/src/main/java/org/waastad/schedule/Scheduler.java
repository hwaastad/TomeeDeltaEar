/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.schedule;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import org.waastad.repository.CustomerRepository;

/**
 *
 * @author Helge Waastad <helge@waastad.org>
 */
@Singleton
public class Scheduler {

    @Inject
    private CustomerRepository customerRepository;

    @Schedule(hour = "*", minute = "*", second = "*/10")
    public void doStuff() {
        System.out.println("Found " + customerRepository.count() + " customers");
    }
}
