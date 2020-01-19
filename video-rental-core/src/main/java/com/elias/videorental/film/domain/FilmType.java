package com.elias.videorental.film.domain;

import lombok.Getter;

@Getter
public enum FilmType {
	NEW(1800, 2, "New Release"),
	REG(1200, 7, "Regular Rental"),
	OLD(900, 14, "Old Film");

	private FilmType(int price, int rentalDaysDuration, String description) {
		this.price = price;
		this.rentalDaysDuration = rentalDaysDuration;
		this.description = description;
	}
	private int price;
	private int rentalDaysDuration;
	private String description;
}