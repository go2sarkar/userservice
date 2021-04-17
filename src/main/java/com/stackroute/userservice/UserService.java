package com.stackroute.userservice;

import com.stackroute.user.exception.UserAlreadyExistException;
import com.stackroute.user.exception.UserNotFoundException;
import com.stackroute.user.model.User;

public interface UserService {
	
	public User registerUser(User user) throws UserAlreadyExistException;

	
	public boolean validateUser(String userName, String password) throws UserNotFoundException;
	
	public boolean deleteUser(String userId);

}
