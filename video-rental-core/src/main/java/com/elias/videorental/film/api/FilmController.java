package com.elias.videorental.film.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.elias.videorental.film.application.CreateFilmCommand;
import com.elias.videorental.film.application.FilmApplicationService;
import com.elias.videorental.film.exception.CreateMovieException;
import com.elias.videorental.shared.Id;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@CrossOrigin
@RestController
@RequestMapping(path = FilmController.PATH, consumes = { APPLICATION_JSON_VALUE }, produces = { APPLICATION_JSON_VALUE })
public class FilmController {

	public static final String PATH = "/api/v1/film";

	@Autowired
	private FilmApplicationService service;

	@Profile("nosecure")
	@ApiOperation(value = "Create Movie", httpMethod = "POST", consumes = APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Film created"),
		@ApiResponse(code = 400, message = "Film could not be created") })
	@PostMapping
	ResponseEntity<Void> create(@RequestBody CreateFilmDto dto) {
		
		var cmd = CreateFilmCommand.of(dto.getName(), dto.getGenre(), dto.getType(), dto.getCost());
		
		UUID id = service.handle(cmd);
		return ResponseEntity.created(
							UriComponentsBuilder.fromPath(PATH).path("/" + id)
						.build()
					.toUri()).build();
	}
}
