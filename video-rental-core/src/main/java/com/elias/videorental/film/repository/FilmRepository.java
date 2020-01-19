package com.elias.videorental.film.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elias.videorental.film.domain.Film;

@Repository
public interface FilmRepository extends CrudRepository<Film, String>{
}
