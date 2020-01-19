package com.elias.videorental.movie.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.elias.videorental.movie.model.enums.Genre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

	@NotNull
	private String name;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Genre genre;
	@NotNull
	@javax.persistence.Id
	private UUID id;
	
	@Builder
	private Movie(UUID id, String name, Genre genre) {
		this.id = id;
		this.name = name;
		this.genre = genre;
	}
}
