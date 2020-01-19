package com.elias.videorental.customer.domain;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CPF {

	@NotNull
	private String numero;
	private String arquivo;
	
	private CPF(String numero, String arquivo) {
		this.numero = numero;
		this.arquivo = arquivo;
	}
	
	public static CPF of(String numero, String arquivo) {
		return new CPF(numero, arquivo);
	}

	public static CPF of(String numero) {
		return new CPF(numero, null);
	}
}
