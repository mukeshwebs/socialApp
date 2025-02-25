package com.hashedin.huspark.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hashedin.huspark.Entity.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long>{
	Comment save(Comment comment);
	ArrayList<Comment> findCommentByPostPostID(Long postID);
	ArrayList<Comment> findCommentByUserUserID(String userID);
	Comment findByCommentID(Long commentID);
}