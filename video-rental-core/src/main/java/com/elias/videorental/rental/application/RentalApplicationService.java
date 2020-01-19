package com.elias.videorental.rental.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import com.elias.videorental.customer.repository.CustomerDomainRepository;
import com.elias.videorental.film.domain.Film;
import com.elias.videorental.film.repository.FilmRepository;
import com.elias.videorental.rental.domain.Rental;
import com.elias.videorental.rental.repository.IRentalRepository;

@Service
@Transactional
public class RentalApplicationService {

	@Autowired
	private IRentalRepository repo;
	@Autowired
	private CustomerDomainRepository customerRepo;
	@Autowired
	private FilmRepository filmRepo;
	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;
	public Rental handle(final CreateRentalCommand cmd) {
		var customer = customerRepo.findById(UUID.fromString(cmd.getCustomerId())).orElseThrow(() -> {
			throw new RuntimeException("Customer not found");
		});
		ArrayList<String> filmsCollection = new ArrayList<String>();
		var films = filmRepo.findAllById(cmd.getFilmsId());
		ArrayList<Film> filmsToValidate = new ArrayList<Film>();
		var iterator = films.iterator(); 
		while (iterator.hasNext()) {
			var film = iterator.next();
			filmsCollection.add(film.getId().toString());
			filmsToValidate.add(film);
			iterator.remove();
		}
		
		var rental = Rental.builder()
				.id(UUID.randomUUID())
				.rentalStart(new Date())
				.rentalEnd(new Date())
				.customerId(customer.getId())
				.filmsId(filmsCollection).build();
		rental.setCreditCost(filmsToValidate, customer, messageSource);
		int rest = customer.getCredits() - rental.getCreditCost();
		rental.setRentalStartEndDates(rental.setRentalType(filmsToValidate));
		rental.setCreditCost(rest);
		repo.save(rental);
		customer.setCredits(rest);
		customerRepo.saveAndFlush(customer);
		return rental;
	}
}
