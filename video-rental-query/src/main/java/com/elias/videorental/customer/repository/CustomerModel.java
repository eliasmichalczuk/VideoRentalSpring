package com.elias.videorental.customer.repository;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customer")
public class CustomerModel {

	@NotNull
	@javax.persistence.Id
	private UUID id;
	@NotNull
	private String name;
	@NotNull
	private Date birth;
	@NotNull
	private String numeroCpf;
	@NotNull
	private String arquivoCpf;
	@NotNull
	private int credits;
	
	// missing address
}
