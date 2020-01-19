package com.elias.videorental.customer.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elias.videorental.customer.domain.Customer;

@Repository
public interface CustomerDomainRepository extends JpaRepository<Customer, UUID>{

	Customer findByNumeroCpf(String cpf);
	Optional<Customer> findById(UUID id);
}
