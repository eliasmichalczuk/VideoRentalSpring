package com.elias.videorental.customer.application;

import java.util.UUID;

import lombok.Data;

@Data(staticConstructor = "of")
public class InsertCreditsToCustomerCommand {

	private final int credits;
	private final UUID customerId;
}
