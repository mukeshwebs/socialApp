package com.hashedin.huspark.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hashedin.huspark.Entity.User;
import com.hashedin.huspark.Repository.UserRepo;

@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public boolean existsByUsername(String userName) {
		return userRepo.existsByUserName(userName);  // Fixed method to match userName
	}

	public void save(User user) {
		userRepo.save(user);  // Fixed method
	}

	public User submitMetaDataOfUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));  // Password encoding
		return userRepo.save(user);  // Save user to the repository
	}

	public User displayUserMetaData(String userID) {
		return userRepo.findByUserID(userID);  // Fetch user by ID
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(userName);  // Load user by username
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + userName);
		}
		return user;  // Return user object as it implements UserDetails
	}

	public ArrayList<User> findAllUser() {
		return userRepo.findAll();  // Fetch all users
	}

	public ArrayList<User> findUserByKeyWord(String keyWord) {
		return userRepo.findUserByKeyWord(keyWord);  // Search users by keyword
	}

	public User updateUser(User updatedUser) {
		User existingUser = userRepo.findById(updatedUser.getUserID()).orElse(null);  // Check if user exists
		if (existingUser != null) {
			// Update fields
			existingUser.setUserName(updatedUser.getUserName());
			existingUser.setAddress(updatedUser.getAddress());
			existingUser.setAvatarURL(updatedUser.getAvatarURL());
			existingUser.setDob(updatedUser.getDob());
			existingUser.setFirstName(updatedUser.getFirstName());
			existingUser.setGender(updatedUser.getGender());
			existingUser.setLastName(updatedUser.getLastName());
			return userRepo.save(existingUser);  // Save updated user
		} else {
			return null;  // Return null if user doesn't exist
		}
	}
}
