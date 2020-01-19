package com.elias.videorental.rental.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;

import com.elias.videorental.customer.domain.Customer;
import com.elias.videorental.film.domain.Film;
import com.elias.videorental.film.domain.FilmType;
import com.elias.videorental.film.domain.RentalType;
import com.elias.videorental.rental.exceptions.VideoRentalCustomerDoesNotHaveEnoughCreditException;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Rental {

	@NotNull
	@javax.persistence.Id
	private UUID id;
	@NotNull
	private Date rentalStart;
	@NotNull
	private Date rentalEnd;
	@NotNull
	@Enumerated(EnumType.STRING)
	private RentalType rentalType;
	private int creditCost;
	@NotNull
	private UUID customerId;
	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
	private List<String> filmsId;
	
	public FilmType setRentalType(List<Film> films) {
		creditCost = 0;
		films.stream().filter(film -> FilmType.NEW.equals(film.getFilmType())).findAny().ifPresent(obj -> {
			this.rentalType = RentalType.NEW;
		});
		if (rentalType != null) {
			return FilmType.NEW;
		}
		films.stream().filter(film -> FilmType.REG.equals(film.getFilmType())).findAny().ifPresent(obj -> {
			this.rentalType = RentalType.REGULARFILMS;
		});
		if (rentalType != null) {
			return FilmType.REG;
		}
		this.rentalType = RentalType.ONLYOLDFILMS;
		return FilmType.OLD;
	}
	
	public void setCreditCost(ArrayList<Film> films, Customer customer, ReloadableResourceBundleMessageSource messageSource) {
		int creditCost = (int) films.stream().reduce(0, (subtotal, film) -> subtotal + film.getFilmType().getPrice(), Integer::sum);
		if (customer.getCredits() < creditCost) {
			throw new VideoRentalCustomerDoesNotHaveEnoughCreditException(
					HttpStatus.BAD_REQUEST, customer.getCredits(), customer.getId().toString(), creditCost, messageSource).get();
		}
		this.setCreditCost(creditCost);
	}

	@SuppressWarnings("deprecation")
	public void setRentalStartEndDates(FilmType filmType) {
		rentalStart = new Date();
		rentalEnd = new Date();
		rentalEnd.setDate(rentalEnd.getDate() + filmType.getRentalDaysDuration());
	}
}
