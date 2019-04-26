package com.myposts.services.users;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myposts.services.users.exceptions.UserNotFoundException;
import com.myposts.services.users.exceptions.WrongPasswordException;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    @Qualifier("passwordEncoder")
    private BCryptPasswordEncoder encoder;

    public List<User> getAllUsers() {
	return userRepo.findAll();
    }

    public void addUser(User user) {
	userRepo.save(user);
    }

    public User findByName(String userName) {
	return userRepo.findByuserName(userName);
    }

    public String valiateUser(String userName, String password) throws UserNotFoundException, WrongPasswordException {
	User user = findByName(userName);
	if (user == null) {
	    throw new UserNotFoundException();
	}

	if (!encoder.matches(password, user.getUserPassword())) {
	    throw new WrongPasswordException();
	}

	String basicAuthHeader = userName + ":" + password;
	basicAuthHeader = "Basic " + Base64.getEncoder().encodeToString(basicAuthHeader.getBytes());
	return basicAuthHeader;
    }
}
