package com.elias.videorental.shared;

import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class Id {

	@javax.persistence.Id
	private UUID id;
	
	private Id(UUID id) {
		this.id = id;
	}
	
	public static Id generate() {
		return new Id(UUID.randomUUID());
	}
	
	@Override
	public String toString() {
		return id.toString();
	}
}
