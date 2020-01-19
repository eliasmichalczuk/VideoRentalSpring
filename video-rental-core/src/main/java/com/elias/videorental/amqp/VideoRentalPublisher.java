package com.elias.videorental.amqp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.elias.videorental.film.amqp.events.FilmCreatedEvent;
import com.elias.videorental.film.domain.Genre;

@Component
@EnableBinding(VideoRentalExchange.class)
public class VideoRentalPublisher {

	private final RabbitTemplate rabbitTemplate;
	private final VideoRentalExchange videoRentalExchange;
	private final Receiver receiver;
	private static final Logger LOG = LoggerFactory.getLogger(VideoRentalPublisher.class);
	public VideoRentalPublisher(Receiver receiver, RabbitTemplate rabbitTemplate, VideoRentalExchange ex) {
		this.receiver = receiver;
		this.rabbitTemplate = rabbitTemplate;
		this.videoRentalExchange = ex;
	}

	private final <T> Message<T> message(T val, VideoRentalExchange videoRentalExchange) {
		try {
			return (Message<T>) org.springframework.messaging.support.MessageBuilder
					.withPayload(val).setHeader("event", val.getClass().getField("NAME").get(val)).build();
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			LOG.debug("Exchange: ", videoRentalExchange);
		}
		return null;
	}
	
	@StreamListener(VideoRentalExchange.INPUT)
	public <T> void publish(T event) {
		System.out.println("Sending message...");
		videoRentalExchange.output().send(message(event, videoRentalExchange));
	}

//	@StreamListener(VideoRentalExchange.INPUT)
//	public <T> void run(T event) throws Exception {
//		var movie = MovieCreatedEvent.builder().name("test movie").movieId("890a172b-6141-42e0-a5c7-9cb085853c6c")
//				.Genre(Genre.Mystery).build();
//		System.out.println("Sending message...");
////		rabbitTemplate.convertAndSend(VideoRentalApplication.exchangeName, VideoRentalApplication.exchangeName, movie);
//		videoRentalExchange.output().send(message(movie, videoRentalExchange));
////	  rabbitTemplate.convertAndSend(VideoRentalApplication.exchangeName, VideoRentalApplication.exchangeName, "OII");
//	}

}