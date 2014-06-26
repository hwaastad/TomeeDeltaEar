/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;
import org.waastad.entity.DeltaCustomer;
import org.waastad.repository.CustomerRepository;

/**
 *
 * @author Helge Waastad <helge@waastad.org>
 */
@Stateless
public class DeltaBean {

    @Inject
    private CustomerRepository customerRepository;

    public DeltaCustomer save(DeltaCustomer customer) {
        return customerRepository.save(customer);
    }

    public DeltaCustomer lookupCustomer(String name) {
        return customerRepository.findCustomerByName(name);
    }

    public DeltaCustomer update(DeltaCustomer customer) {
        DeltaCustomer c = customerRepository.findBy(customer.getId());
        c.setName(customer.getName());
        customerRepository.save(c);
        return c;
    }
}
