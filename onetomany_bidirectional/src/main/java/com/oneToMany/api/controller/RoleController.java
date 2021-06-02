package com.oneToMany.api.controller;

import com.oneToMany.api.model.Role;
import com.oneToMany.api.repository.RoleRepository;
import com.oneToMany.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
  public List<Role> getListOfRoles(){
    return roleRepository.findAll();
  }
  
}
