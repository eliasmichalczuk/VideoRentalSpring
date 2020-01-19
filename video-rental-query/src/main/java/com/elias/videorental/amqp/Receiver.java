package com.elias.videorental.amqp;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.elias.videorental.movie.amqp.events.MovieCreatedEvent;

@Component
public class Receiver {

  public void receiveMessage(MovieCreatedEvent message) {
    System.out.println(message);
  }
}