package com.elias.videorental.movie.amqp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.Message;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.elias.videorental.VideoRentalApplication;
import com.elias.videorental.amqp.VideoRentalExchange;
import com.elias.videorental.film.amqp.events.FilmCreatedEvent;
import com.elias.videorental.film.application.CreateFilmCommand;
import com.elias.videorental.film.application.FilmApplicationService;
import com.elias.videorental.film.domain.Genre;

@ActiveProfiles(profiles = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = VideoRentalApplication.class, webEnvironment=WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@Transactional
public class FilmCreatedPublisherTest {

	@MockBean
	private VideoRentalExchange exchange;
	@Autowired
	private FilmApplicationService service;
//	@Autowired
//	private VideoRentalPublisher publisher;
	@Before()
	public void setup() {
		Mockito.when(exchange.output()).thenReturn(Mockito.mock(SubscribableChannel.class));
	}
	
//	@Test
//	public void shouldEmitMovieCreatedEventOnVideoRentalExchange() {
//		var cmd = CreateFilmCommand.of("João da silva antônio", Genre.Drama);
//		service.handle(cmd);
//		ArgumentCaptor<Message<?>> argument = ArgumentCaptor.forClass(Message.class);
//		verify(exchange.output()).send(argument.capture());
//		var event = FilmCreatedEvent.class;
//		FilmCreatedEvent paylod = event.cast(((Message<?>) argument.getValue().getPayload()).getPayload());
//		assertThat(paylod.getName()).isEqualTo("João da silva antônio1");
//		assertThat(paylod.getGenre()).isEqualTo(Genre.Drama);
//	}
//	
//	@Test
//	public void shouldEmitNullOnVideoRentalExchange() {
//		publisher.publish(null);
//		ArgumentCaptor<Message<?>> argument = ArgumentCaptor.forClass(Message.class);
//		verify(exchange.output()).send(argument.capture());
//		var event = MovieCreatedEvent.class;
//		var paylod = event.cast(((Message<?>) argument.getValue()));
//		assertThat(paylod).isEqualTo("123");
//	}
}
