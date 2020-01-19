package com.elias.videorental.customer.application;

import java.util.UUID;
import java.util.function.Consumer;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elias.videorental.amqp.VideoRentalPublisher;
import com.elias.videorental.customer.amqp.events.CustomerCreatedEvent;
import com.elias.videorental.customer.domain.Customer;
import com.elias.videorental.customer.exception.CustomerNotFoundException;
import com.elias.videorental.customer.repository.CustomerDomainRepository;

@Service
@Transactional
public class CustomerApplicationService {

	@Autowired
	private CustomerDomainRepository repo;

	@Autowired
	private VideoRentalPublisher publisher;

	public UUID handle(final CreateCustomerCommand cmd) {
		Customer customer = Customer.builder()
				.numeroCpf(cmd.getCpf().getNumero())
				.arquivoCpf(cmd.getCpf().getArquivo())
				.name(cmd.getName())
				.birth(cmd.getBirth())
				.credits(0)
				.id(UUID.randomUUID()).build();
		if(repo.findByNumeroCpf(cmd.getCpf().getNumero()) != null) {
			throw new RuntimeException("There is already a Costumer wih this CPF");
		}
		this.repo.save(customer);
		this.publisher.publish(CustomerCreatedEvent
				.builder().numeroCpf(customer.getNumeroCpf())
				.arquivoCpf(customer.getArquivoCpf())
				.name(customer.getName()).customerId(customer.getId())
				.birth(customer.getBirth()).build());
		return customer.getId();
	}
	
	public void handle(final InsertCreditsToCustomerCommand cmd) {
		repo.findById(cmd.getCustomerId()).ifPresentOrElse(customer -> {
			customer.setCredits(cmd.getCredits() + customer.getCredits());
			this.repo.save(customer);
		}, () -> {
			throw new CustomerNotFoundException();
		});
		
	}
}
