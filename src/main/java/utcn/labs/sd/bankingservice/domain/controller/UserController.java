package utcn.labs.sd.bankingservice.domain.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utcn.labs.sd.bankingservice.core.configuration.SwaggerTags;

import utcn.labs.sd.bankingservice.domain.dto.UserDTO;
import utcn.labs.sd.bankingservice.domain.service.UserService;

import java.util.List;

import javax.validation.Valid;

@Api(tags = { SwaggerTags.BANKING_SERVICE_TAG })
@RestController
@RequestMapping("/bank/admin/employee")
@CrossOrigin
class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "getAllEmployees", tags = SwaggerTags.EMPLOYEE_TAG)
	@GetMapping
	public List<UserDTO> getAllEmployees() {
		return userService.getAllEmployee();
	}

	@ApiOperation(value = "findEmployeeById", tags = SwaggerTags.CLIENT_TAG)
	@GetMapping(value = "/{userId}")
	public ResponseEntity<?> findEmployeeById(@PathVariable("userId") Integer userId) {
		try {
			UserDTO userDto = userService.getEmployeeById(userId);
			return new ResponseEntity<>(userDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "insertEmployee", tags = SwaggerTags.EMPLOYEE_TAG)
	@PostMapping
	public ResponseEntity<?> insertEmployee(@Valid @RequestBody UserDTO userDto) {
		UserDTO userDtoToBeInserted;
		try {
			userDtoToBeInserted = userService.insertEmployee(userDto);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(userDtoToBeInserted, HttpStatus.CREATED);
	}

	@ApiOperation(value = "updateEmployee", tags = SwaggerTags.EMPLOYEE_TAG)
	@PutMapping(value = "/{userId}")
	public ResponseEntity<?> updateEmployee(@PathVariable("userId") Integer userId,@Valid @RequestBody UserDTO userDto) {
		try {
			UserDTO changedEmployee = userService.updateEmployee(userId, userDto);
			return new ResponseEntity<>(changedEmployee, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "deleteEmployee", tags = SwaggerTags.EMPLOYEE_TAG)
	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("userId") Integer userId) {
		try {
			userService.deleteEmployee(userId);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (NotFoundException ne) {
			return new ResponseEntity<>(ne.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
