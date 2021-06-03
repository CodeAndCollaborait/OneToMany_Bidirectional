package com.oneToMany.api.controller;

import com.oneToMany.api.exception.DataNotFoundException;
import com.oneToMany.api.model.Role;
import com.oneToMany.api.model.User;
import com.oneToMany.api.service.RoleService;
import com.oneToMany.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private RoleService roleService;
  
  @GetMapping("/list")
  public ResponseEntity<?> getUserList() {
	return userService.getListOfUsers();
  }
  
  @PostMapping("/{role_ID}/create")
  public ResponseEntity<?> createUserByRoleID
		  (@PathVariable(value = "role_ID") long role_ID, @RequestBody Role role
		  ) throws DataNotFoundException {
	return userService.updateRoleByID(role_ID, role);
  }
  
  @PutMapping("/update/{id}")
  public ResponseEntity<?> updateUser(@PathVariable(value = "id") long id,
									  @RequestBody User user) throws DataNotFoundException {
	return userService.updateUserDetails(id, user);
  }
  
  
}
