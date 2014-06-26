/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.view;

import java.io.Serializable;
import java.util.Date;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import org.apache.deltaspike.core.util.ExceptionUtils;
import org.waastad.ejb.DeltaBean;
import org.waastad.entity.DeltaCustomer;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@Named
@ViewAccessScoped
public class ViewController implements Serializable {

    private static final long serialVersionUID = 2302175898239971184L;
    @Inject
    private DeltaBean deltaBean;
    private String name;

    public void doAction(ActionEvent event) {
        System.out.println("doing jsf action");
        DeltaCustomer c = new DeltaCustomer(name);
        deltaBean.save(c);
    }

    public void lookup(ActionEvent event) {
        try {
            DeltaCustomer c = deltaBean.lookupCustomer(name);
            c.setName(name + "-new");
            deltaBean.update(c);
        } catch (Exception e) {
            System.out.println("Got Exception: " + e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
