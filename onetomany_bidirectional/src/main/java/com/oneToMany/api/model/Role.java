package com.oneToMany.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "role_table")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Role extends AuditModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(nullable = false)
  private String roleName;
  
  @Column(nullable = false)
  @Size(min = 25, max = 255)
  private String description;
  
  @OneToMany(targetEntity = User.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "role_id")
  private List<User> users;
  
}
