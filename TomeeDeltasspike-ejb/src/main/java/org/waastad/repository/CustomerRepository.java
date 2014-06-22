/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.repository;

import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.waastad.entity.DeltaCustomer;

/**
 *
 * @author Helge Waastad <helge@waastad.org>
 */
@Repository
public abstract class CustomerRepository extends AbstractEntityRepository<DeltaCustomer, Long> {

}
