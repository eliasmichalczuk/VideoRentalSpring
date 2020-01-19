package com.elias.videorental.movie.repository;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elias.videorental.movie.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public interface MovieRepository extends JpaRepository<MovieModel, UUID>{

}
