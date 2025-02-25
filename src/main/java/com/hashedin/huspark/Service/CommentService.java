package com.hashedin.huspark.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hashedin.huspark.Entity.Comment;
import com.hashedin.huspark.Repository.CommentRepo;

@Service
public class CommentService {
	@Autowired
	CommentRepo commentRepo;
	
	public Comment saveComment(Comment comment)
	{
		return commentRepo.save(comment);
	}
	
	public ArrayList<Comment> findCommentByPostID(Long postID)
	{
		return commentRepo.findCommentByPostPostID(postID);
	}
	
	public ArrayList<Comment> findCommentByUserID(String userID){
		return commentRepo.findCommentByUserUserID(userID);
	}
}
