/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.jms;

import java.io.Serializable;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
public class EventMessage implements Serializable {

    private static final long serialVersionUID = 3860546400350796961L;

    private String message;
    private Object customer;

    public EventMessage() {
    }

    public EventMessage(String message) {
        this.message = message;
    }

    public EventMessage(String message, Object customer) {
        this.message = message;
        this.customer = customer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getCustomer() {
        return customer;
    }

    public void setCustomer(Object customer) {
        this.customer = customer;
    }

}
