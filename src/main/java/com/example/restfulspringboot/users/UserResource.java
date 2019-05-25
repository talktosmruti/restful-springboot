package com.example.restfulspringboot.users;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	UserDaoService userDao;
	
	@GetMapping()
	public List<User> getAllusers(){
		return this.userDao.fetchAllUsers();
	}
	
	@GetMapping("/{userId}")
	@ApiOperation(notes = "Returns details of a user by taking the id as input", value = "get user")
	public Resource<User> getUserById(@PathVariable String userId) {
		User user = this.userDao.getUserById(Integer.parseInt(userId));
		
		ControllerLinkBuilder link = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).getAllusers());
		Resource<User> resource = new Resource<User>(user, link.withRel("all-users"));
		return resource;
	}
	
	@PostMapping()
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		User userCreated = this.userDao.createUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userCreated.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
