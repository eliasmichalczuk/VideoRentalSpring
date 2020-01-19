package com.elias.videorental.movie.repository.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elias.videorental.movie.amqp.events.MovieCreatedEvent;
import com.elias.videorental.movie.model.Movie;
import com.elias.videorental.movie.repository.MovieModel;
import com.elias.videorental.movie.repository.MovieRepository;

@Service
@Transactional
public class MovieService {

	@Autowired
	private MovieRepository repo;
	
	public void handle(MovieCreatedEvent event) {
		repo.save(MovieModel.builder().name(event.getName()).id(event.getMovieId()).genre(event.getGenre()).build());
	}
}
