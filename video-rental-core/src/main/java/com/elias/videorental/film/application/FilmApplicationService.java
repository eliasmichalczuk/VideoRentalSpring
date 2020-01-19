package com.elias.videorental.film.application;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elias.videorental.amqp.VideoRentalPublisher;
import com.elias.videorental.film.amqp.events.FilmCreatedEvent;
import com.elias.videorental.film.domain.Film;
import com.elias.videorental.film.repository.FilmRepository;
import com.elias.videorental.shared.Id;

@Service
@Transactional
public class FilmApplicationService {

	@Autowired
	private FilmRepository movieRepository;

	@Autowired
	private VideoRentalPublisher publisher;

	public UUID handle(final CreateFilmCommand cmd) {
		Film movie = Film.builder().id(UUID.randomUUID().toString()).filmType(cmd.getType()).name(cmd.getName()).genre(cmd.getGenre()).build();
		this.movieRepository.save(movie).getId();
		this.publisher.publish(FilmCreatedEvent.builder().movieId(UUID.fromString(movie.getId())).name(cmd.getName()).Genre(cmd.getGenre()).build());
		return UUID.fromString(movie.getId());
	}
}
