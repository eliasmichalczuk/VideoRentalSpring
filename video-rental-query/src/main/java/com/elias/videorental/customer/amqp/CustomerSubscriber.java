package com.elias.videorental.customer.amqp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.elias.videorental.amqp.VideoRentalExchange;
import com.elias.videorental.customer.amqp.events.CustomerCreatedEvent;
import com.elias.videorental.customer.repository.service.CustomerService;
import com.elias.videorental.movie.amqp.events.MovieCreatedEvent;

@EnableBinding(VideoRentalExchange.class)
public class CustomerSubscriber {

	@Autowired
	private CustomerService service;
	
	@StreamListener(target = VideoRentalExchange.OUTPUT, condition = CustomerCreatedEvent.CONDITIONAL_EXPRESSION)
	public void movieCreatedEvent(CustomerCreatedEvent event) {
		System.out.println("received customer event");
		System.out.println(event.getName());
		service.handle(event);
	}
}
