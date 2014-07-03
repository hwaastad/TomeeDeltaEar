/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.waastad.ejb.DeltaBean;
import org.waastad.entity.DeltaCustomer;
import org.waastad.entity.DeltaUser;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@Named
@ViewAccessScoped
public class ViewController implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(ViewController.class);

    private static final long serialVersionUID = 2302175898239971184L;
    @Inject
    private DeltaBean deltaBean;
    private String name;
    private List<DeltaCustomer> dataList = new ArrayList<>();
    private DeltaCustomer customer;
    private DeltaUser user;

    @PostConstruct
    public void init() {
        dataList.addAll(deltaBean.findAll());
    }

    public void prepare(ActionEvent event) {
        customer = new DeltaCustomer();
    }

    public void prepareUser(ActionEvent event) {
        user = new DeltaUser();
    }

    public void prepareView(ActionEvent event) {
        customer = deltaBean.lookupCustomerById(customer.getId());
        LOG.info("Preparing view for customer {}",customer.getName());
    }

    public void saveUser(ActionEvent event) {
        customer = deltaBean.addUser(customer, user);
        Messages.addGlobalInfo("User saved!");
        LOG.info("User {} saved",user.getUsername());
    }
    
    public void deleteUser(ActionEvent event){
        deltaBean.deleteUser(customer, user);
        customer.getUserCollection().remove(user);
        Messages.addGlobalInfo("User deleted!");
        LOG.info("User {} deleted",user.getUsername());
    }

    public void save(ActionEvent event) {
        customer = deltaBean.save(customer);
        dataList.add(customer);
        Messages.addGlobalInfo("Customer saved!");
        LOG.info("Customer {} saved",customer.getName());
    }

    public void delete(ActionEvent event) {
        deltaBean.delete(customer);
        dataList.remove(customer);
        Messages.addGlobalInfo("Customer deleted!");
        LOG.info("Customer {} deleted",customer.getName());
    }

    public void update(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            deltaBean.update(customer);
            context.update("mainForm:table");
            Messages.addGlobalInfo("Customer updated!");
        } catch (Exception e) {
            LOG.error("Got Exception: {}", e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeltaCustomer> getDataList() {
        return dataList;
    }

    public void setDataList(List<DeltaCustomer> dataList) {
        this.dataList = dataList;
    }

    public DeltaCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(DeltaCustomer customer) {
        this.customer = customer;
    }

    public DeltaUser getUser() {
        return user;
    }

    public void setUser(DeltaUser user) {
        this.user = user;
    }

}
