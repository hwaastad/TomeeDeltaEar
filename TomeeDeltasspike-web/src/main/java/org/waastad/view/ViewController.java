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

    public void doAction(ActionEvent event) {
        System.out.println("doing jsf action");
        DeltaCustomer c = new DeltaCustomer("actionname: " + new Date().toString());
        deltaBean.save(c);
    }

}
