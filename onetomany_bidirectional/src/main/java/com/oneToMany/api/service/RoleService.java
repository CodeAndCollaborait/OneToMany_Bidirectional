package com.oneToMany.api.service;

import com.oneToMany.api.exception.DataNotFoundException;
import com.oneToMany.api.model.Role;
import com.oneToMany.api.repository.RoleRepository;
import com.oneToMany.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.crypto.Data;
import java.util.List;

@Service
public class RoleService {
  
  @Autowired
  private RoleRepository roleRepository;
  
  @Autowired
  private UserRepository userRepository;
  
  //new Role with new Users
  @Transactional
  public ResponseEntity<Object> addRoleWithUser(Role role) {
	Role newRole = new Role();
	newRole.setRoleName(role.getRoleName());
	newRole.setDescription(role.getDescription());
	newRole.setUsers(role.getUsers());
	
	Role saveRole = roleRepository.save(newRole);
	
	if (roleRepository.findById(saveRole.getId()).isPresent()) {
	  return ResponseEntity.accepted().
			  body("Successfully Created Role with Users");
	} else
	  return ResponseEntity.unprocessableEntity()
			  .body("Failed to created role with users");
  }
  
  //Delete Role
  public ResponseEntity<?> deleteRoleByID(long id) {
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
  
  //Read the Role and user details
  public ResponseEntity<?> readRole(long id) throws DataNotFoundException {
	Role role = roleRepository.findById(id).orElseThrow(() ->
			new DataNotFoundException("Given id not found in Database:  " + id));
	return ResponseEntity.ok(role);
  }
  
  //Read list of all roles
  public ResponseEntity<?> readListOfRole() {
	List<Role> role = roleRepository.findAll();
	return ResponseEntity.ok().body(role);
  }
  
  
  //Update existing Role
  public ResponseEntity<?> updateRoleByID(long id, Role role) throws DataNotFoundException {
	Role newRole = roleRepository.findById(id).orElseThrow(
			() -> new DataNotFoundException("Role ID not found in DB " + id));
	newRole.setRoleName(role.getRoleName());
	newRole.setDescription(role.getDescription());
	newRole.setUsers(role.getUsers());
	
	Role updatedRole = roleRepository.save(newRole);
	return ResponseEntity.ok(updatedRole);
  }
  
}
