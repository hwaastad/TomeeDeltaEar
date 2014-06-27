/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.waastad.entity.DeltaCustomer;
import org.waastad.entity.DeltaUser;
import org.waastad.repository.CustomerRepository;
import org.waastad.repository.UserRepository;
import org.waastad.schedule.Scheduler;

/**
 *
 * @author Helge Waastad <helge@waastad.org>
 */
@Stateless
public class DeltaBean {

    private static final Logger LOG = LoggerFactory.getLogger(DeltaBean.class);

    @Inject
    private CustomerRepository customerRepository;
    @Inject
    private UserRepository userRepository;

    public DeltaCustomer save(DeltaCustomer customer) {
        LOG.info("Saving customer");
        return customerRepository.save(customer);
    }

    public DeltaCustomer lookupCustomer(String name) {
        LOG.info("Lookup customer");
        return customerRepository.findCustomerByName(name);
    }

    public DeltaCustomer update(DeltaCustomer customer) {
        LOG.info("Update customer, user collection: {}", customer.getUserCollection().size());
        DeltaCustomer c = customerRepository.findBy(customer.getId());
        c.setName(customer.getName());
        c.setUserCollection(customer.getUserCollection());
        customerRepository.save(c);
        return c;
    }

    public DeltaCustomer addUsertoCustomer(DeltaCustomer customer, DeltaUser user) {
        DeltaCustomer c = customerRepository.findBy(customer.getId());
        userRepository.save(user);
        c.getUserCollection().add(user);
        return c;
    }

    public void delete(DeltaCustomer customer) {
        customer = customerRepository.findBy(customer.getId());
        customerRepository.remove(customer);
    }

    public List<DeltaCustomer> findAll() {
        return customerRepository.findAll();
    }
}
