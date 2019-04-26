package com.myposts.services.posts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myposts.services.users.User;
import com.myposts.services.users.UsersService;

@Service
public class PostsService {

    @Autowired
    private UsersService userService;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private PostsDao postsDao;

    public void savePost(String userName, String content, boolean isPrivate) {
	User user = userService.findByName(userName);
	if (user != null) {
	    Post post = new Post(content, isPrivate, user);
	    postsRepository.save(post);
	}
    }

    public List<Post> findUserPosts(String userName) {
	User user = userService.findByName(userName);
	return user != null ? postsRepository.findByUser(user) : new ArrayList<>();
    }

    public List<Post> searchPublicPostsBySearchString(String searchString) {
	return postsDao.findPublicPostsBySearchString(searchString);
    }
}
