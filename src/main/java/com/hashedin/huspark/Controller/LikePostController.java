package com.hashedin.huspark.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hashedin.huspark.Entity.LikePost;
import com.hashedin.huspark.Service.LikePostService;

@RestController
@RequestMapping("/likePost")
public class LikePostController {

	@Autowired
	LikePostService likePostService;

	@PostMapping("/saves")
	private LikePost likePost(@RequestParam Long postID) {
		// Extract userID from the currently authenticated user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userID = authentication.getName();  // This assumes the username is the userID

		// Perform the like action
		return likePostService.likePost(userID, postID);
	}

	@DeleteMapping("/delete")
	private boolean unlikePost(@RequestParam Long postID) {
		// Extract userID from the currently authenticated user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userID = authentication.getName();  // This assumes the username is the userID

		// Perform the unlike action
		return likePostService.deleteLikePost(userID, postID);
	}

	@GetMapping("/findTotal")
	private ArrayList<LikePost> findUserPostByUserID(@RequestParam Long postID) {
		return likePostService.findLikePostByPost(postID);
	}

	@GetMapping("/isLike")
	private boolean isExist(@RequestParam Long postID) {
		// Extract userID from the currently authenticated user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userID = authentication.getName();  // This assumes the username is the userID

		// Check if the user has liked the post
		return likePostService.checkExistByUserIDAndPostID(userID, postID);
	}
}
