package com.oneToMany.api.controller;

import com.oneToMany.api.exception.DataNotFoundException;
import com.oneToMany.api.model.Role;
import com.oneToMany.api.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/role")
@Tag(name = "Role API", description = "Role API controller")
public class RoleController {
  
  @Autowired
  private RoleService roleService;
  
  
  @PostMapping("/create")
  public ResponseEntity<Object> createRoleWithUser(@RequestBody Role role) {
	return roleService.addRoleWithUser(role);
  }
  
  @Operation(summary = "Get the list of all the roles", tags = {"roles"})
  @ApiResponses(value = {@ApiResponse(responseCode = "200",
		  description = "Found the list of roles",
		  content = {@Content(mediaType = "application/json",
				  array = @ArraySchema(schema = @Schema(implementation = Role.class))
		  ),})})
  @ApiResponse(responseCode = "400", description = "Invalid Request"
		  , content = @Content)
  @GetMapping("/list")
  public ResponseEntity<?> getListOfRoles() {
	return roleService.readListOfRole();
  }
  
  @Operation(summary = "Get Role by ID")
  @ApiResponses(value = {@ApiResponse(responseCode = "200",
		  description = "Found Role by specific ID",
		  content = {@Content(mediaType = "application/json",
				  schema = @Schema(implementation = Role.class)
		  ),})})
  @ApiResponse(responseCode = "404", description = "ID not found in database"
		  , content = @Content)
  @GetMapping("/{id}")
  public ResponseEntity<?> getRoleByID(@PathVariable(value = "id") long id)
		  throws DataNotFoundException {
	return roleService.readRole(id);
  }
  
  
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteRole(@PathVariable(value = "id") long id) {
	return roleService.deleteRoleByID(id);
  }
  
  
  @PutMapping("update/{id}")
  public ResponseEntity<?> updateRole(@PathVariable(value = "id") long id,
									  @RequestBody Role role) throws DataNotFoundException {
	return roleService.updateRoleByID(id, role);
  }
  
}
