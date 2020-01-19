package com.elias.videorental.customer.api;

import java.util.Set;

import lombok.Data;
import lombok.Getter;

@Data(staticConstructor = "of")
public class RentalCommandDto {

	private final String customer;
	private final Set<String> filmsId;
}
