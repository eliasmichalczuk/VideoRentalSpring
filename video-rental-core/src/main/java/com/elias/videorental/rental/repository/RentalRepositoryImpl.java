package com.elias.videorental.rental.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.elias.videorental.rental.domain.Rental;

@Repository
public class RentalRepositoryImpl implements RentalRepositoryCustom {
	
	@Autowired
	EntityManager em;

	@Override
	public List<Rental> findAllByDateStartLessDateEndGreaterThanDateCriteria() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		Date date = new Date();
		CriteriaQuery<Rental> query = builder.createQuery(Rental.class);
		Root<Rental> rental = query.from(Rental.class);
		
		Predicate rentalHasStarted = builder.lessThanOrEqualTo(rental.get("rentalStart"), date);
		Predicate rentalHasNotEnded = builder.greaterThanOrEqualTo(rental.get("rentalEnd"), date);
		query.where(builder.and(rentalHasStarted, rentalHasNotEnded));
		return em.createQuery(query).getResultList();
	}
}
