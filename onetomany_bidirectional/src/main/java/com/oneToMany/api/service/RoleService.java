package com.oneToMany.api.service;

import com.oneToMany.api.model.Role;
import com.oneToMany.api.repository.RoleRepository;
import com.oneToMany.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {
  
  @Autowired
  private RoleRepository roleRepository;
  
  @Autowired
  private UserRepository userRepository;
  
  
  //new Role with new Users
  
  @Transactional
  public ResponseEntity<Object> addRoleWithUser(Role role){
    Role newRole = new Role();
    newRole.setRoleName(role.getRoleName());
    newRole.setDescription(role.getDescription());
    newRole.setUsers(role.getUsers());
    
    Role saveRole = roleRepository.save(newRole);
    
    if (roleRepository.findById(saveRole.getId()).isPresent()){
      return ResponseEntity.accepted().
			  body("Successfully Created Role with Users");
	}else
	  return ResponseEntity.unprocessableEntity()
			  .body("Failed to created role with users");
  }
  
  
}
