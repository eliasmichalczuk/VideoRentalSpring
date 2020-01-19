package com.elias.videorental.movie.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class CreateMovieException extends ConstraintViolationException{

	public CreateMovieException(Set<? extends ConstraintViolation<?>> constraintViolations) {
		super(constraintViolations);
	}

	private static final long serialVersionUID = -334806424859911637L;

}
