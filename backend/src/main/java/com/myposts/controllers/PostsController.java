package com.myposts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myposts.services.posts.Post;
import com.myposts.services.posts.PostsService;
import com.myposts.services.users.exceptions.InvalidApiParameterException;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/posts")
public class PostsController {

    @Autowired
    private PostsService postsService;

    @PostMapping("/save")
    public ResponseEntity<String> savePost(@RequestParam("username") String username,
	    @RequestParam("post") String content,
	    @RequestParam("isPrivate") boolean isPrivate) {
	postsService.savePost(username, content, isPrivate);
	return ResponseEntity.ok("Post Saved");
    }

    @GetMapping("/getUserPosts")
    public List<Post> getUserPosts(@RequestParam("username") String username) {
	return postsService.findUserPosts(username);
    }

    @GetMapping("/search")
    @ApiOperation(value = "Search through public posts by specified string")
    @ApiResponses(value = @ApiResponse(code = 422, message = "Search string is either null/empty/only whitespaces"))
    public List<Post> searchPublicPosts(@RequestParam("searchString") String searchString) throws InvalidApiParameterException {
	if (searchString == null || searchString.trim().isEmpty()) {
	    throw new InvalidApiParameterException("Search string mustn't be null/empty/only whitespaces");
	}
	return postsService.searchPublicPostsBySearchString(searchString);
    }
}
