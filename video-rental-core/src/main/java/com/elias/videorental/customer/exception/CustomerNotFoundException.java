package com.elias.videorental.customer.exception;

import lombok.Getter;

@Getter
public class CustomerNotFoundException  extends RuntimeException {

	private static final long serialVersionUID = -6538629233252015186L;

	public CustomerNotFoundException() {
	}
}