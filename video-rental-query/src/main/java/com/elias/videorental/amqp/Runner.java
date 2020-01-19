package com.elias.videorental.amqp;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.elias.videorental.VideoRentalQueryApplication;

@Component
public class Runner implements CommandLineRunner {

  private final RabbitTemplate rabbitTemplate;

  public Runner(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public void run(String... args) throws Exception {
//    System.out.println("Sending message...");
//    rabbitTemplate.convertAndSend(VideoRentalQueryApplication.exchangeName, VideoRentalQueryApplication.exchangeName, "Hello from RabbitMQ!");
  }

}