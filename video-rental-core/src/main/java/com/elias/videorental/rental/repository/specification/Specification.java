package com.elias.videorental.rental.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.CriteriaQuery;

public interface Specification<T> {

	boolean isSatisfiedBy(T t);

	Predicate toPredicate(Root<T> root, CriteriaBuilder cb);
	Predicate toPredicate(Root<T> root, CriteriaQuery query, CriteriaBuilder cb);

	Class<T> getType();
}
