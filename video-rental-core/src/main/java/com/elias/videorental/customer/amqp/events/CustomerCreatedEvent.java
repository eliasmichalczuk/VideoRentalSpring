package com.elias.videorental.customer.amqp.events;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.elias.videorental.customer.domain.CPF;
import com.elias.videorental.film.amqp.events.FilmCreatedEvent;
import com.elias.videorental.film.amqp.events.FilmCreatedEvent.FilmCreatedEventBuilder;
import com.elias.videorental.film.domain.Genre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerCreatedEvent implements Serializable {

	private static final long serialVersionUID = -1991710643551734796L;
	public static final transient String NAME = "CustomerCreatedEvent";
	public static final transient String ORIGIN = "VideoRentalCore";
	public static final transient String CONDITIONAL_EXPRESSION = "headers['event']=='" + NAME + "'";

	@NonNull
	private UUID customerId;
	@NonNull
	private String name;
	@NonNull
	private String numeroCpf;
	@NonNull
	private String arquivoCpf;
	@NonNull
	private Date birth;
	
}
