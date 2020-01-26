package com.example.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@PostMapping(path="/add")
	public @ResponseBody String addNewUser(@RequestParam String name,@RequestParam String email) {
		
		User user=new User();
		user.setName(name);
		user.setEmail(email);
		
		userRepository.save(user);
		return "Saved";
	}
	
	@RequestMapping(value="/id/{id}",method=RequestMethod.GET)
	public  @ResponseBody Optional<User> getById(@PathVariable("id") int userId) {
		return userRepository.findById(userId);
	}
	
	@GetMapping(path="/")
	public @ResponseBody Iterable<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@DeleteMapping(value="/id/{id}")
	public @ResponseBody String deleteById(@PathVariable("id")int id) {
		userRepository.deleteById(id);
		return "Deleted";
	}
	
	
}
