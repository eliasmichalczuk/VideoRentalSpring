package com.elias.videorental.customer.repository.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elias.videorental.customer.amqp.events.CustomerCreatedEvent;
import com.elias.videorental.customer.repository.CustomerModel;
import com.elias.videorental.customer.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository repo;
	
	public void handle(CustomerCreatedEvent event) {
		repo.save(CustomerModel.builder()
				.id(event.getCustomerId())
				.name(event.getName())
				.arquivoCpf(event.getArquivoCpf())
				.numeroCpf(event.getNumeroCpf())
				.birth(event.getBirth()).build());
	}
}
