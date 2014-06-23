/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.ejb;

import javax.ejb.EJB;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.junit.Module;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.waastad.repository.CustomerRepository;

/**
 *
 * @author helge
 */
//@RunWith(ApplicationComposer.class)
public class DeltaBeanTest {

//    @ClassRule
//    public static final EJBContainerRule CONTAINER_RULE = new EJBContainerRule();
//
//    @Rule
//    public final InjectRule injectRule = new InjectRule(this, CONTAINER_RULE);
    public DeltaBeanTest() {
    }

//    @EJB
//    private DeltaBean deltaBean;
//
//    @Mock
//    private CustomerRepository customerRepository;
//
//    @Module
//    public Class<?>[] classes() {
//        return new Class<?>[]{DeltaBean.class, CustomerRepository.class};
//    }

    /**
     * Test of save method, of class DeltaBean.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
    }

    /**
     * Test of lookupCustomer method, of class DeltaBean.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testLookupCustomer() throws Exception {
        System.out.println("lookupCustomer");
    }

}
