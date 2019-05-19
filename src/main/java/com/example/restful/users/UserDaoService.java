package com.example.restful.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoService {
	
	private static List<User> users= new ArrayList<>();
	
	private static int userCount;
	
	static {
		users.add(new User(1, "Juju", new Date()));
		users.add(new User(2, "Mama", new Date()));
		users.add(new User(3, "baba", new Date()));
		userCount = users.size();
	}
	
	public List<User> fetchAllUsers(){
		return users;
	}

	public User getUserById(Integer id) {
		for ( User user: users) {
			if(id.equals(user.getId())){
				return user;
			}
		}
		 throw new UserNotFoundException("Not found id "+id);
	}
	
	public User createUser(User user) {
		if(user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
}
