package com.myposts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myposts.services.users.UsersService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/")
public class LoginController {

    @Autowired
    private UsersService userService;

    @PostMapping(value = "/login")
    public String login(@RequestParam("username") String userName,
	    @RequestParam("password") String password) {
	try {
	    return userService.valiateUser(userName, password);
	} catch (Exception e) {
	    return "";
	}
    }
}
