package com.stackroute.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.user.exception.UserAlreadyExistException;
import com.stackroute.user.exception.UserNotFoundException;
import com.stackroute.user.model.User;
import com.stackroute.userservice.UserService;
import com.stackroute.userservice.jwt.JwtUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	JwtUtil jwtUtil;

	public UserController(UserService userService) {
		super();
		this.userService=userService;
	}

	@GetMapping("/health")
	@ApiOperation(value = "Get the status of user service", notes="Provide the successfull response with text", response = String.class)
	public String health(){
		return "I am alive";
	}

	@PostMapping("/register")
	@CrossOrigin(origins = "*")
	@ApiOperation(value = "Register an user with user details", notes="Allows user to register with details", response = ResponseEntity.class)
	public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistException, ServletException{
		HttpHeaders headers=new HttpHeaders();	
		//headers.add("Access-Control-Allow-Origin", "*");
		if(user==null){
			return new ResponseEntity<User>(user,headers,HttpStatus.BAD_REQUEST);
		}
		try{
			user= userService.registerUser(user);
			if(user != null){
				headers.add("user created -",String.valueOf(user.getUserId()));
				return new ResponseEntity<User>(headers,HttpStatus.CREATED);
			}else{
				return new ResponseEntity<User>(headers,HttpStatus.CONFLICT);
			}
		}catch(Exception ex){
			return new ResponseEntity<User>(headers,HttpStatus.CONFLICT);
		}
	}
	

	@PostMapping("/validate")
	@CrossOrigin(origins = "*")
	@ApiOperation(value = "Authenicate an user for login with userId and userPassword", notes="It validates user userid and password. After successful validation, it returns jwt token in header response", response = ResponseEntity.class)
	public ResponseEntity<?> authenticateUser(@ApiParam(value = "userId and userPassword are mandatory fields for authentication" )
	                                       @RequestBody User user){
		HttpHeaders headers=new HttpHeaders();	
		Map<String, String> map = new HashMap<>();
		boolean validUser=false;
		
		 try {
				validUser =userService.validateUser(user.getUserId(), user.getUserPassword());
				System.out.println("validUser:" + validUser);
				if(!validUser){
					return new ResponseEntity<>(map,HttpStatus.UNAUTHORIZED);
				}
				

			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				return new ResponseEntity<>(map,HttpStatus.UNAUTHORIZED);
			}

			if (validUser) {
			    String jwtToken = jwtUtil.generateToken(user.getUserId());			    
			    map.put("token", jwtToken);
				
				
			   // map.put("loginUserId", user.getUserId());
				System.out.println("jwtToken:" + jwtToken);
			}			
			
			//headers.add("Access-Control-Allow-Origin", "*");
			return new ResponseEntity<>(map,headers,HttpStatus.OK);

	}
	
	@GetMapping("/delete/{userId}")
	@ApiOperation(value = "Delete an user", notes="It deletes an user", response = ResponseEntity.class)
	public ResponseEntity<?> deleteUser(@ApiParam(value = "Delete an user by userId" ) @PathVariable("userId") String userId) {
		
		if(userService.deleteUser(userId)) {;		
		   return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

}