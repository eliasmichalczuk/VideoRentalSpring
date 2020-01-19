package com.elias.videorental.film.amqp.events;

import java.io.Serializable;
import java.util.UUID;

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
public class FilmCreatedEvent implements Serializable {

	private static final long serialVersionUID = -5069853912402176838L;
	public static final transient String NAME = "MovieCreatedEvent";
	public static final transient String ORIGIN = "VideoRentalCore";
	public static final transient String CONDITIONAL_EXPRESSION = "headers['event']=='" + NAME + "'";

	@NonNull
	private UUID movieId;
	@NonNull
	private String name;
	@NonNull
	private Genre Genre;
}