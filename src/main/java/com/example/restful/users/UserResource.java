package com.example.restful.users;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public User getUserById(@PathVariable String userId) {
		return this.userDao.getUserById(Integer.parseInt(userId));
	}
	
	@PostMapping()
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		User userCreated = this.userDao.createUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userCreated.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
