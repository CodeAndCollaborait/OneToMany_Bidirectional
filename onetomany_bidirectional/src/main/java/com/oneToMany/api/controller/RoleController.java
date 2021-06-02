package com.oneToMany.api.controller;

import com.oneToMany.api.exception.DataNotFoundException;
import com.oneToMany.api.model.Role;
import com.oneToMany.api.repository.RoleRepository;
import com.oneToMany.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/role")
public class RoleController {
  
  @Autowired
  private RoleService roleService;
  
  @Autowired
  private RoleRepository roleRepository;
  
  @PostMapping("/create")
  public ResponseEntity<Object> createRoleWithUser(@RequestBody Role role) {
	return roleService.addRoleWithUser(role);
  }
  
  @GetMapping("/rolelist")
  public List<Role> getListOfRoles() {
	return roleRepository.findAll();
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Role> getRoleByID(@PathVariable(value = "id") long id) throws DataNotFoundException {
	Role role = roleRepository.findById(id).orElseThrow(() ->
			new DataNotFoundException("Given id not found in Database:  " + id));
	
	return ResponseEntity.ok(role);
  }
  
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Object> deleteRole(@PathVariable(value = "id") long id) {
	
	if (roleRepository.findById(id).isPresent()) {
	  roleRepository.deleteById(id);
	  if (roleRepository.findById(id).isPresent()) {
		return ResponseEntity.unprocessableEntity()
				.body("Failed to delete given id: " + id);
	  } else
		return ResponseEntity.ok().body("Successfully Deleted ID: " + id);
	} else
	  return ResponseEntity.unprocessableEntity().body("No Record found");
  }
  
  
}
