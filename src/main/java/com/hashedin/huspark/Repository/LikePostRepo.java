package com.hashedin.huspark.Repository;



import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hashedin.huspark.Entity.LikePost;
import com.hashedin.huspark.Entity.Post;

@Repository
public interface LikePostRepo extends JpaRepository<LikePost, Long> {
	LikePost save(LikePost likePost);
	ArrayList<LikePost> findAll();
	ArrayList<LikePost> findLikePostByPost(Post post);
	ArrayList<LikePost> findLikePostByUserUserIDAndPostPostID(String userID, Long postID);
}
