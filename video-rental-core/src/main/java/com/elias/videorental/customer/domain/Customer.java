package com.elias.videorental.customer.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

	@NotNull
	@javax.persistence.Id
	private UUID id;
	@NotNull
	private String name;
	@NotNull
	private Date birth;
	@NotNull
	private int credits;
	@NotNull
	private String numeroCpf;
	@NotNull
	private String arquivoCpf;
	
	// missing address
}
