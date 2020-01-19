package com.elias.videorental.customer.api;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data(staticConstructor = "of")
public class CreateCommandCpfDto {

	@NotNull
	private final String numero;
	@NotNull
	private final String arquivo;
}
