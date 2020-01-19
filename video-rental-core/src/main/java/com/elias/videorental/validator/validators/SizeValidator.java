package com.elias.videorental.validator.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.elias.videorental.validator.contraints.SizeConstraint;

public class SizeValidator implements ConstraintValidator<SizeConstraint, String> {

	private SizeConstraint constraint;

	@Override
	public void initialize(SizeConstraint constraint) {
		this.constraint = constraint;
	}

	@Override
	public boolean isValid(String field, ConstraintValidatorContext cxt) {
		return field != null && field.length() <= constraint.max() && field.length() >= constraint.min();
	}

}