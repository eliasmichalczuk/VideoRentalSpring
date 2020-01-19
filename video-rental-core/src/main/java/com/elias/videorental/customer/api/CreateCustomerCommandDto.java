package com.elias.videorental.customer.api;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.elias.videorental.customer.domain.CPF;
import com.elias.videorental.validator.contraints.SizeConstraint;

import lombok.Data;

@Data(staticConstructor = "of")
public class CreateCustomerCommandDto {

	@NotNull
	@SizeConstraint(min = 3, max = 100, message = "dto.customer.name")
	private final String name;
	@NotNull
	private final Date birth;
	@NotNull
	private final CPF cpf;
}
