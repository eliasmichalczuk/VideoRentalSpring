package com.elias.videorental.validator.contraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.elias.videorental.validator.validators.SizeValidator;

@Documented
@Constraint(validatedBy = SizeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EliasBadRequestConstraint {
	String[] args();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}