package com.stackroute.userservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.user.dao.UserRepository;
import com.stackroute.user.exception.UserAlreadyExistException;
import com.stackroute.user.exception.UserNotFoundException;
import com.stackroute.user.model.User;



@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
    private UserRepository userRepository;
	/*
	 * This method should be used to save a new user.
	 */

	public User registerUser(User user) throws UserAlreadyExistException {
		Optional<User> exitingUser = userRepository.findById(user.getUserId());
		if(exitingUser.isPresent()){
			throw new UserAlreadyExistException("User already Exits");
		}else{
			return userRepository.save(user);
		}

	}

		

	/*
	 * This method should be used to validate a user using userId and password.
	 */

	public boolean validateUser(String userId, String password) throws UserNotFoundException {
		
		Optional<User> exitingUser = userRepository.findById(userId);
		
		if(exitingUser.isPresent()){
			if (exitingUser.get().getUserPassword()!= null && exitingUser.get().getUserPassword().equals(password)){
				return true;
			}
			else{
				return false;
			}
			
		}
		else{
			throw new UserNotFoundException("User not found exception");
		}

	}	
	
	public boolean deleteUser(String userId) {
		try {
	     userRepository.deleteById(userId);
		}
		catch (Exception ex) {
			return false;
		}
		return true;

	}

}
