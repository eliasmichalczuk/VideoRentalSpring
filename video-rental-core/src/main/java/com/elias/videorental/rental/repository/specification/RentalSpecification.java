package com.elias.videorental.rental.repository.specification;

import java.lang.reflect.ParameterizedType;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.CriteriaQuery;

import com.elias.videorental.rental.domain.Rental;

public class RentalSpecification {

//	public static Specification<Rental> rentalStartDateIsLess() {
//		return (rental, query, cb) -> cb.equal(rental.get("rentalStart"), new Date());
//	}

	public boolean isSatisfiedBy(Rental rental) {
		return rental.getRentalStart().before(new Date()) && rental.getRentalEnd().after(new Date());
	}

	public Predicate toPredicate(Root<Rental> rental, CriteriaBuilder cb) {
		return cb.and(cb.lessThan(rental.get("rental_start"), new Date()),
				cb.greaterThan(rental.get("rental_end"), new Date()));
	}

	@SuppressWarnings("unchecked")
	public Class<Rental> getType() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		return (Class<Rental>) type.getActualTypeArguments()[0];
	}

	public Predicate toPredicate(Root<Rental> root, CriteriaQuery query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		return null;
	}
}
