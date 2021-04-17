package com.stackroute.user.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.user.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,String> {

}
