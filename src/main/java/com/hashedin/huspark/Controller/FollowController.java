package com.hashedin.huspark.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hashedin.huspark.Entity.Follow;
import com.hashedin.huspark.Service.FollowService;

@RestController
@RequestMapping("/follow")
public class FollowController {
	@Autowired
	FollowService flwSer;
	
	@GetMapping("/isFollow")
	private boolean checkExist(@RequestParam String followerID,@RequestParam String targetID) {
		return flwSer.checkExist(followerID, targetID);
	}
	
	@PostMapping("/save")
	private Follow saveFollow(@RequestBody Follow fl) {
		return flwSer.startFollow(fl);
	}
	
	@DeleteMapping("/delete")
	private boolean dltFollow(@RequestParam String followerID,@RequestParam String targetID) {
		return flwSer.delFollow(followerID, targetID);
	}
}
