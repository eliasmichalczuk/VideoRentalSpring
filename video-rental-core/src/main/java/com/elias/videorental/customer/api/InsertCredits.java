package com.elias.videorental.customer.api;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data(staticConstructor = "of")
public class InsertCredits {

	@NotNull
	@Min(1)
	@Max(10000)
	private final int credits;
	private final String name;
}
