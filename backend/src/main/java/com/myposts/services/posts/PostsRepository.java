package com.myposts.services.posts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myposts.services.users.User;

public interface PostsRepository extends JpaRepository<Post, Long> {

    List<Post> findByUser(User user);
}
