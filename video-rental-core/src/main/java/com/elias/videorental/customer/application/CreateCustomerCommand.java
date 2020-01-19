package com.elias.videorental.customer.application;

import java.util.Date;
import java.util.UUID;

import com.elias.videorental.customer.domain.CPF;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCustomerCommand {

	private final UUID id;
	private final String name;
	private final Date birth;
	private final CPF cpf;
	private final int credits;
}
