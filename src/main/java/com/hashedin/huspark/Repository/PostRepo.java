package com.hashedin.huspark.Repository;

import java.util.ArrayList;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hashedin.huspark.Entity.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
	Post save(Post post);

	Post findByPostID(Long postID);

	ArrayList<Post> findAll();

	@Query("SELECT p FROM Post p WHERE p.imageURL IS NOT NULL")
	ArrayList<Post> findPostImageURLNotNull();

	@Query("SELECT p FROM Post p WHERE p.imageURL IS NOT NULL AND p.user.userID = :userID")
	ArrayList<Post> findPostImageURLNotNullUser(@Param("userID") String userID);

	@Query("SELECT p FROM Post p ORDER BY p.createDay DESC")
	ArrayList<Post> findRecommendedPosts(PageRequest pageReq);
	
	@Query("SELECT p FROM Post p WHERE p.user.userID=:userID ORDER BY p.createDay DESC")
	ArrayList<Post> findRecommendedPostsByUser(PageRequest pageReq, @Param("userID") String userID);
}
