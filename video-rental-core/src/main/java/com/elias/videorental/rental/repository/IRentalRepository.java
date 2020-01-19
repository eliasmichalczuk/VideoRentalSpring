package com.elias.videorental.rental.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elias.videorental.rental.domain.Rental;
import com.elias.videorental.rental.repository.specification.RentalSpecification;
import com.elias.videorental.rental.repository.specification.Specification;
import com.google.common.base.Optional;

public interface IRentalRepository extends JpaRepository<Rental, UUID> {

	Optional<List<Rental>> findByCustomerId(UUID id);
}
