package com.elias.videorental.rental.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
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

import com.elias.videorental.rental.application.CreateRentalCommand;
import com.elias.videorental.rental.application.RentalApplicationService;
import com.elias.videorental.rental.domain.Rental;
import com.elias.videorental.rental.repository.IRentalRepository;
import com.elias.videorental.rental.repository.RentalRepositoryCustom;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(path = RentalController.PATH, consumes = { APPLICATION_JSON_VALUE }, produces = { APPLICATION_JSON_VALUE })
public class RentalController {

	public static final String PATH = "/api/v1/rental";

	@Autowired
	private RentalApplicationService service;
	
	@Autowired
	private IRentalRepository repo;
	@Autowired
	private RentalRepositoryCustom criteriaRrepo;

	@Profile("nosecure")
	@ApiOperation(value = "Create Rental", httpMethod = "POST", consumes = APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Rental created"),
		@ApiResponse(code = 400, message = "Rental could not be created") })
	@PostMapping
	ResponseEntity<Rental> create(@RequestBody CreateRentalCommandDto dto) {
		
		var cmd = CreateRentalCommand.of(dto.getCustomerId(), dto.getFilmsId());
		
		Rental rental = service.handle(cmd);
		return ResponseEntity.ok(rental).created(
							UriComponentsBuilder.fromPath(PATH).path("/" + rental.getId())
						.build()
					.toUri()).build();
	}
	
	@ApiOperation(value = "Returns rentals from a customer", httpMethod = "GET")
	@GetMapping(path = "/customerId/{id}")
	public List<Rental> byCustomerId(@PathVariable String id) {
		return repo.findByCustomerId(UUID.fromString(id)).get();
	}
	
	@ApiOperation(value = "Ongoing rentals", httpMethod = "GET")
	@GetMapping(path = "/onGoingRentals")
	public List<Rental> onGoingRentals() {
		return criteriaRrepo.findAllByDateStartLessDateEndGreaterThanDateCriteria();
	}
}