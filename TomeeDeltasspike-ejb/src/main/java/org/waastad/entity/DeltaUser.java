/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@Entity
@SequenceGenerator(name="deltauser_seq", initialValue=1, allocationSize=100,sequenceName = "deltauser_seq")
public class DeltaUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @TableGenerator(table = "ID_TABLE", name = "UserIdTable",
//            allocationSize = 1000, initialValue = 0, pkColumnName = "pk",
//            valueColumnName = "value", pkColumnValue = "user")
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "UserIdTable")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "deltauser_seq")
    private Long id;

    @Basic
    @Column(nullable = false)
    @NotNull
    private String username;

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
        if (!(object instanceof DeltaUser)) {
            return false;
        }
        DeltaUser other = (DeltaUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.waastad.entity.DeltaUser[ id=" + id + " ]";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
