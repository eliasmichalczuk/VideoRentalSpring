package com.elias.videorental.customer.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class VideoRentalCreateCustomerException extends ConstraintViolationException {
	
	private static final long serialVersionUID = 7686045486654120992L;

	public VideoRentalCreateCustomerException(Set<? extends ConstraintViolation<?>> constraintViolations) {
		super(constraintViolations);
	}
}
