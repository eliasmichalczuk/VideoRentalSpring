package com.elias.videorental.rental.application;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data(staticConstructor = "of")
public class CreateRentalCommand {

	@NotNull
	private final String customerId;
	@NotNull
	private final List<String> filmsId;
}
