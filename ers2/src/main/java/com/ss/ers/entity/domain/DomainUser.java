package com.ss.ers.entity.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="usr")
@Data
public class DomainUser {
    
    @Id
    @Column(name="user_id")
   private Integer userId;
   
   private String email;
   
   private String password;
   
   @Enumerated(EnumType.STRING)
   private Roles roles;
}
