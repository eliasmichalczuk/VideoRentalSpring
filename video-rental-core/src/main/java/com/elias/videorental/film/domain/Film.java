package com.elias.videorental.film.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@DynamicInsert
@DynamicUpdate
@Table(name="film")
@Builder
public class Film {

	@NotNull
	private String name;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Genre genre;
	@NotNull
	@javax.persistence.Id
	private String id;
	@NotNull
	@Enumerated(EnumType.STRING)
	private FilmType filmType;
}
