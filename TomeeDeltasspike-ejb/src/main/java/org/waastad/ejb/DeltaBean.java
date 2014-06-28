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
    
    public DeltaCustomer lookupCustomerById(Long id) {
        LOG.info("Lookup customerId");
        return customerRepository.findBy(id);
    }

    public DeltaCustomer update(DeltaCustomer customer) {
        LOG.info("Update customer {}", customer.getId());
        customerRepository.findBy(customer.getId());
        return customerRepository.save(customer);
    }

    public DeltaCustomer addUsertoCustomer(DeltaCustomer customer) {
        customerRepository.findBy(customer.getId());
        return  customerRepository.save(customer);
    }

    public void delete(DeltaCustomer customer) {
        customer = customerRepository.findBy(customer.getId());
        customerRepository.remove(customer);
    }

    public List<DeltaCustomer> findAll() {
        List<DeltaCustomer> list = customerRepository.findAll();
        return list;
    }
    
    public List<DeltaCustomer> findAllCache() {
        return customerRepository.findAllCacheOnly();
    }
}
