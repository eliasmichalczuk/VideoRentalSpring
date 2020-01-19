package com.elias.videorental.film.application;

import javax.validation.constraints.NotNull;

import com.elias.videorental.film.domain.FilmType;
import com.elias.videorental.film.domain.Genre;

import lombok.Data;
import lombok.Getter;

@Getter
@Data(staticConstructor = "of")
public class CreateFilmCommand {

	@NotNull
	private final String name;
	@NotNull
	private final Genre genre;
	@NotNull
	private final FilmType type;
	@NotNull
	private final int cost;
}
