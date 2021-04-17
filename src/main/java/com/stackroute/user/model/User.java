package com.stackroute.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name="User")
@ApiModel(description = "Details about the user")
public class User implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="userId")	
	@ApiModelProperty(notes = "Unique id of the user")
	private String userId;
	
	@Column(name="firstName")
	@ApiModelProperty(notes = "First name of the user")
	private String firstName;
	
	@Column(name="lastName")
	@ApiModelProperty(notes = "Last name of the user")
	private String lastName;	
	
	public User() {	
	}	

	public User(String userId, String firstName, String lastName, String userPassword) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userPassword = userPassword;
	}



	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", userPassword="
				+ userPassword + "]";
	}

	@Column(name="userPassword")
	@ApiModelProperty(notes = "Password of the user for login")
	private String userPassword;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}
