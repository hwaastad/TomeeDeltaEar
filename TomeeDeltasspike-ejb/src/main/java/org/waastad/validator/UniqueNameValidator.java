/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.validator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.waastad.entity.DeltaCustomer;
import org.waastad.repository.CustomerRepository;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@ApplicationScoped
public class UniqueNameValidator implements ConstraintValidator<UserName, DeltaCustomer> {

    @Inject
    private CustomerRepository customerRepository;

    @Override
    public void initialize(UserName a) {
    }

    @Override
    public boolean isValid(DeltaCustomer t, ConstraintValidatorContext cvc) {
        return this.customerRepository.findCustomerByName(t.getName()) == null;
    }

}
