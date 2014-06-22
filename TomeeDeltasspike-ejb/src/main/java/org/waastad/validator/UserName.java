/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.waastad.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.inject.Default;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@NotNull(groups = Default.class)
@Size(min = 2, max = 9, message = "invalid name", groups = Default.class)
@Constraint(validatedBy = UniqueNameValidator.class)
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserName {

    String message() default "{org.waastad.validator.UserName}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
