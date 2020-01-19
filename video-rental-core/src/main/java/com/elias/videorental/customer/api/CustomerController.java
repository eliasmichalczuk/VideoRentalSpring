package com.elias.videorental.customer.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.elias.videorental.customer.application.CreateCustomerCommand;
import com.elias.videorental.customer.application.CustomerApplicationService;
import com.elias.videorental.customer.application.InsertCreditsToCustomerCommand;
import com.elias.videorental.customer.exception.VideoRentalCreateCustomerException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(path = CustomerController.PATH, consumes = { APPLICATION_JSON_VALUE }, produces = { APPLICATION_JSON_VALUE })
public class CustomerController {

	public static final String PATH = "/api/v1/customer";

	@Autowired
	private CustomerApplicationService service;
	@Autowired
	Validator validator;

	@Profile("nosecure")
	@ApiOperation(value = "Create customer", httpMethod = "POST", consumes = APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Customer created"),
		@ApiResponse(code = 400, message = "Customer could not be created") })
	@PostMapping(path = "")
	ResponseEntity<Void> create(@RequestBody CreateCustomerCommandDto dto) {
		
		var errors = validator.validate(dto);
		if (!errors.isEmpty()) {
			throw new VideoRentalCreateCustomerException(errors);
		}
		var cmd = CreateCustomerCommand.builder()
				.name(dto.getName())
				.birth(dto.getBirth())
				.cpf(dto.getCpf())
				.build();
		
		UUID id = service.handle(cmd);
		return ResponseEntity.created(
							UriComponentsBuilder.fromPath(PATH).path("/" + id)
						.build()
					.toUri()).build();
	}
	
	@Profile("nosecure")
	@ApiOperation(value = "Add customer credit", httpMethod = "POST")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Credit added to customer"),
			@ApiResponse(code = 400, message = "Could not add credits to customer")})
	@PostMapping(path = "/{id}/credit")
	 ResponseEntity<Void>  insertCredits(@Valid @RequestBody InsertCredits dto, @PathVariable String id) {
		this.service.handle(InsertCreditsToCustomerCommand.of(dto.getCredits(), UUID.fromString(id)));
		return ResponseEntity.ok().build();
	}
}
