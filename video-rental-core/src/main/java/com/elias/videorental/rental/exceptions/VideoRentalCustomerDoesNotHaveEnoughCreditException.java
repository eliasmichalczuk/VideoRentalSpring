package com.elias.videorental.rental.exceptions;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.elias.videorental.validator.contraints.VideoRentalCustomExeceptions;

import lombok.Getter;

@Getter
public class VideoRentalCustomerDoesNotHaveEnoughCreditException {
	

	
	private int customerCredits;
	private String customerId;
	private int rentaltotalRequiredCredits;
	private HttpStatus status;
	private String message;
	

	public VideoRentalCustomerDoesNotHaveEnoughCreditException(
			HttpStatus status, int customerCredits, String customerId, int rentaltotalRequiredCredits, ReloadableResourceBundleMessageSource messageSource) {
this.status = status;
		//		super(status, dateFormat);
		Locale locale = LocaleContextHolder.getLocale();
		this.message = messageSource.getMessage(this.getClass().getSimpleName().toString() + ".message",null,locale).toString();
		
		this.customerCredits = customerCredits;
		this.rentaltotalRequiredCredits = rentaltotalRequiredCredits;
		this.customerId = customerId;
		message = message.replace("{0}", ""+this.customerId)
						.replace("{1}", ""+this.customerCredits)
						.replace("{2}", ""+this.rentaltotalRequiredCredits);
	}
	private static final long serialVersionUID = -6538629233252015186L;
	
	public ResponseStatusException get() {
		return new ResponseStatusException(status, message);
	}
}

