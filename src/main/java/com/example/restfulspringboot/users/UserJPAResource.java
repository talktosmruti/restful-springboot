package com.example.restfulspringboot.users;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users/jpa")
public class UserJPAResource {

	@Autowired
	UserJPARepository userJPARepository;
	
	@Autowired
	PostRepository postRepository;
	
	@GetMapping()
	public List<User> getAllusers(){
		return this.userJPARepository.findAll();
	}
	
	@GetMapping("/{userId}")
	@ApiOperation(notes = "Returns details of a user by taking the id as input", value = "get user")
	public Resource<User> getUserById(@PathVariable String userId) {
		Optional<User> user = this.userJPARepository.findById(Integer.parseInt(userId));
		
		ControllerLinkBuilder link = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).getAllusers());
		Resource<User> resource = new Resource<User>(user.get(), link.withRel("all-users"));
		return resource;
	}
	
	@PostMapping()
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		User userCreated = this.userJPARepository.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userCreated.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/posts/{id}")
	public List<Post> getPostsByUser(@PathVariable String id){
		Optional<User> user = userJPARepository.findById(Integer.parseInt(id));
		if(!user.isPresent()) {
			throw new UserNotFoundException("Invlaid user");
		}
		
		return user.get().getPosts();
	}
	
	@PostMapping("/posts/{id}")
	public ResponseEntity<Object> savePost(@PathVariable String id, @RequestBody Post post){
		Optional<User> user = userJPARepository.findById(Integer.parseInt(id));
		if(!user.isPresent()) {
			throw new UserNotFoundException("Invlaid user");
		}
		
		Post createdPost= postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(createdPost.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
}
