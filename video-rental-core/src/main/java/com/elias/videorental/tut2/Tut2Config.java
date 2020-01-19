package com.elias.videorental.tut2;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut2", "work-queues"})
@Configuration
public class Tut2Config {

	@Bean
	public Queue hello() {
			return new Queue("hello");
	}
	
	@Profile("receiver")
	private static class ReceiverConfig {
		@Bean
		public Tut2Receiver reicever1() {
			return new Tut2Receiver(1);
		}
		
		@Bean
		public Tut2Receiver reicever2() {
			return new Tut2Receiver(2);
		}
	}
	
	@Profile("sender")
	@Bean
	public Tut2Sender sender() {
		return new Tut2Sender();
	}
}
