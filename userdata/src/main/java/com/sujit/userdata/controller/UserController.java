package com.sujit.userdata.controller;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sujit.userdata.dao.UserRepository;
import com.sujit.userdata.model.User;

@RestController
@RequestMapping("/info")


public class UserController {
	@Autowired
	private UserRepository repository;

	@PostMapping("/createUser")
	public int create(@RequestBody User user) {

		repository.save(user);
		return user.getId();

	}

	@GetMapping("/getUser/{id}/{email}")
	public User getUser(@PathVariable int id, @PathVariable String email) {
		Optional<User> user = repository.findById(id);

		if (user.isPresent() && email.equals(user.get().getEmail())) {
			return user.get();
		} else {
			throw new RuntimeException("User not Found for the id :" + id + " and email :" + email);
		}

	}

	@PutMapping("/updateUser/{id}")
	public String update(@PathVariable int id, @RequestBody User user) {
		repository.save(user);
		return "Data Updated for id :" + id;
	}

	@DeleteMapping("/deleteUser/{id}")
	public String delete(@PathVariable int id) {
		Optional<User> user = repository.findById(id);
		if (user.isPresent()) {
			repository.delete(user.get());
			return "User is deleted with id :" + id;
		} else {
			throw new RuntimeException("User Not Found with id :" + id);
		}

	}
}
