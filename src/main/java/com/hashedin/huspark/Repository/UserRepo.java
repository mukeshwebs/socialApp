package com.hashedin.huspark.Repository;

import org.springframework.stereotype.Repository;
import com.hashedin.huspark.Entity.User;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

	User save(User user);
	User findByUserID(String userID);
	ArrayList<User> findAll();
	User findByUserName(String userName);

	@Query("SELECT u FROM User u WHERE u.firstName LIKE %:keyword% OR u.lastName LIKE %:keyword% OR u.address LIKE %:keyword% OR u.userName LIKE %:keyword%")
	ArrayList<User> findUserByKeyWord(String keyword);

	boolean existsByUserName(String userName);  // Updated to use userName instead of username
}
