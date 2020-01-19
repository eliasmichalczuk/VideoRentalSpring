package com.elias.videorental.movie.api;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.elias.videorental.movie.model.enums.Genre;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateMovieDto {

	@NotNull
	@Size(min = 1, max = 100, message ="{CreateMovieCommandDto.nome.Size}")
	private String name;
	@NotNull
	private Genre genre;
}
