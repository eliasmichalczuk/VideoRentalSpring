package com.elias.videorental.movie.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.elias.videorental.movie.repository.MovieModel;
import com.elias.videorental.movie.repository.MovieRepository;
import com.elias.videorental.movie.repository.service.MovieService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;


@CrossOrigin
@RestController
@RequestMapping(path = MovieController.PATH, consumes = { APPLICATION_JSON_VALUE }, produces = { APPLICATION_JSON_VALUE })
public class MovieController {

	public static final String PATH = "/api/v1/movie";

	@Autowired
	private MovieRepository repo;

	@ApiOperation(value = "Returns a movie", httpMethod = "GET")
	@GetMapping(path = "/id/{id}")
	public MovieModel byId(@PathVariable String id) {
		
		return repo.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("id " + id + " not found"));
	}
}
