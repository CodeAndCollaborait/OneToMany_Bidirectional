package com.oneToMany.api.service;

import com.oneToMany.api.exception.DataNotFoundException;
import com.oneToMany.api.model.Role;
import com.oneToMany.api.model.User;
import com.oneToMany.api.repository.RoleRepository;
import com.oneToMany.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
  
  
  @Autowired
  private RoleRepository roleRepository;
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private RoleService roleService;
  
  //Read the list of Users
  public ResponseEntity<?> getListOfUsers() {
	return ResponseEntity.ok().body(userRepository.findAll());
  }
  
  
  //Create User by Role id;
  public ResponseEntity<?> addUserByRoleID(long role_id, User user) throws DataNotFoundException {
	
	if (roleRepository.findById(role_id).isPresent()) {
	  return ResponseEntity.ok().body(userRepository.save(user));
	} else
	  return ResponseEntity.unprocessableEntity().body
			  (new DataNotFoundException("Role ID not found: " + role_id));

//	Role role =
//			roleRepository.findById(role_id)
//					.orElseThrow(() -> new DataNotFoundException("Role ID not found: " + role_id));
//
//	List<User> users = new ArrayList<>();
//	users.add(user);
	
  }
  
  public ResponseEntity<?> updateRoleByID(long id, Role role) throws DataNotFoundException {
	Role newRole = roleRepository.findById(id).orElseThrow(
			() -> new DataNotFoundException("Role ID not found in DB " + id));
	
	newRole.setUsers(role.getUsers());
	
	Role updatedRole = roleRepository.save(newRole);
	return ResponseEntity.ok(updatedRole);
  }
  
  public ResponseEntity<?> updateUserDetails(long id, User user) throws DataNotFoundException {
	User updatedUser = userRepository.findById(id)
			.orElseThrow(() -> new DataNotFoundException("User by id not found: " + id));
	
	updatedUser.setName(user.getName());
	updatedUser.setUserName(user.getUserName());
	updatedUser.setEmail(user.getEmail());
	updatedUser.setPassword(user.getPassword());
	
	User newUser = userRepository.save(updatedUser);
	return ResponseEntity.ok().body(newUser);
	
  }
  
  
}
