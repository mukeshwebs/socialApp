package com.hashedin.huspark.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hashedin.huspark.Entity.User;
import com.hashedin.huspark.Service.UserService;

@RestController
@RequestMapping("/users") //
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/saves")
	private User submitUser(@RequestBody User user) {
		return userService.submitMetaDataOfUser(user);  // Save user metadata
	}

	@GetMapping("/{userID}")
	private User getUserDetails(@PathVariable("userID") String userID) {
		return userService.displayUserMetaData(userID);  // Get user details by userID
	}

	@GetMapping("/all")
	private ArrayList<User> findAll() {
		return userService.findAllUser();  // Get all users
	}

	@GetMapping("/search")
	private ArrayList<User> searchUserByKey(@RequestParam String keyWord) {
		return userService.findUserByKeyWord(keyWord);  // Search users by keyword
	}

	@PostMapping("/update")
	private User updateUser(@RequestBody User user) {
		return userService.updateUser(user);  // Update user metadata
	}
}
