package com.elias.videorental.rental.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.elias.videorental.rental.domain.Rental;

@Repository
public interface RentalRepositoryCustom {

	List<Rental> findAllByDateStartLessDateEndGreaterThanDateCriteria();
}
