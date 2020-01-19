package com.elias.videorental.amqp;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface VideoRentalExchange {

	public static final String INPUT = "video-rental-input-events";
	public static final String OUTPUT = "video-rental-output-events";
	
	@Input(VideoRentalExchange.OUTPUT)
	SubscribableChannel output();
	@Input(VideoRentalExchange.INPUT)
	SubscribableChannel input();
}
