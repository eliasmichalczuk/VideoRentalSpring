package com.elias.videorental.movie.amqp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.elias.videorental.amqp.VideoRentalExchange;
import com.elias.videorental.movie.amqp.events.MovieCreatedEvent;
import com.elias.videorental.movie.repository.service.MovieService;

@EnableBinding(VideoRentalExchange.class)
public class MovieSubscriber {

	@Autowired
	private MovieService service;
	
	@StreamListener(target = VideoRentalExchange.OUTPUT, condition = MovieCreatedEvent.CONDITIONAL_EXPRESSION)
	public void movieCreatedEvent(MovieCreatedEvent event) {
		System.out.println("received movie event");
		System.out.println(event.getMovieId().toString());
		System.out.println(event.getName());
		System.out.println(event.getGenre().toString());
		service.handle(event);
	}
}
