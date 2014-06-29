/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

/**
 *
 * @author Helge Waastad <helge@waastad.org>
 */
@Entity
public class DeltaCustomer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @TableGenerator(table = "ID_TABLE", name = "CustomerIdTable",
//            allocationSize = 1000, initialValue = 0, pkColumnName = "pk",
//            valueColumnName = "value", pkColumnValue = "customer")
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CustomerIdTable")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Collection<DeltaUser> userCollection;

    @OneToMany(targetEntity = EventLog.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "customer",referencedColumnName = "id")
    private Collection<EventLog> tEventLogsCollection;

    public DeltaCustomer(String name) {
        this.name = name;
    }

    public DeltaCustomer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeltaCustomer)) {
            return false;
        }
        DeltaCustomer other = (DeltaCustomer) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "org.waastad.entity.Customer[ id=" + id + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<DeltaUser> getUserCollection() {
        if (this.userCollection == null) {
            this.userCollection = new ArrayList<>();
        }
        return this.userCollection;
    }

    public void setUserCollection(Collection<DeltaUser> userCollection) {
        this.userCollection = userCollection;
    }

    public Collection<EventLog> gettEventLogsCollection() {
        return tEventLogsCollection;
    }

    public void settEventLogsCollection(Collection<EventLog> tEventLogsCollection) {
        this.tEventLogsCollection = tEventLogsCollection;
    }

}
