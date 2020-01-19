package com.elias.videorental.amqp;

import org.springframework.stereotype.Component;

import com.elias.videorental.film.amqp.events.FilmCreatedEvent;

@Component
public class Receiver {

	public void receiveMessage(FilmCreatedEvent message) {
	    System.out.println("Received <" + message + ">");
  }

}