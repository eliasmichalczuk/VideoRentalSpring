package com.elias.videorental.film.api;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.elias.videorental.film.domain.FilmType;
import com.elias.videorental.film.domain.Genre;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateFilmDto {

	@NotNull
	@Size(min = 1, max = 100, message ="{CreateMovieCommandDto.nome.Size}")
	private String name;
	@NotNull
	private Genre genre;
	@NotNull
	private FilmType type;
	@NotNull
	private int cost;
}
