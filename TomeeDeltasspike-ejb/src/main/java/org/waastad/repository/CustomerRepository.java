/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.repository;

import java.util.List;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.QueryHint;
import javax.persistence.TypedQuery;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;
import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.QueryHints;
import org.waastad.entity.DeltaCustomer;
import org.waastad.entity.DeltaCustomer_;

/**
 *
 * @author Helge Waastad <helge@waastad.org>
 */
@Repository
public abstract class CustomerRepository extends AbstractEntityRepository<DeltaCustomer, Long> implements CriteriaSupport<DeltaCustomer> {

    public DeltaCustomer findCustomerByName(String name) {
        return criteria()
                .like(DeltaCustomer_.name, name)
                .getOptionalResult();
    }

    public List<DeltaCustomer> findAllFetch() {
        TypedQuery<DeltaCustomer> query = this.entityManager().createQuery("SELECT t FROM DeltaCustomer t JOIN FETCH t.userCollection", DeltaCustomer.class);
        return query.getResultList();
    }

    public List<DeltaCustomer> findAllCacheOnly() {
        TypedQuery<DeltaCustomer> query = this.entityManager().createQuery("SELECT t FROM DeltaCustomer t", DeltaCustomer.class)
                .setHint(QueryHints.QUERY_RESULTS_CACHE ,"TRUE");
        return query.getResultList();
    }
}
