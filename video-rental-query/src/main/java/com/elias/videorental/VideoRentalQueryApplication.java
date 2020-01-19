package com.elias.videorental;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.elias.videorental.amqp.Receiver;
import com.elias.videorental.amqp.VideoRentalExchange;

@EnableScheduling
@SpringBootApplication
@EnableBinding(VideoRentalExchange.class)
public class VideoRentalQueryApplication {

	public static final String exchangeName = "video-rental-exchange";
	static final String queueName = "video-rental-query";

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(exchangeName);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(exchangeName);
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connecFact, MessageListenerAdapter listener) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connecFact);
		container.setQueueNames(queueName);
		container.setMessageListener(listener);
		return container;
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	public static void main(String[] args) {
		SpringApplication.run(VideoRentalQueryApplication.class, args);
	}

//	@Profile("usage_message")
//	@Bean
//	public CommandLineRunner usage() {
//		return args -> {
//			System.out.println("This app uses Spring Profiles to" + " control its behavior.\n");
//			System.out.println("Sample usage: java -jar" + " rabbit-tutorials.jar"
//					+ " --spring.profiles.active=hello-world,sender");
//		};
//	}
//
//	@Profile("!usage_message")
//	@Bean
//	public CommandLineRunner tutorial() {
//		return new AmqpTutorialRunner();
//	}
}
