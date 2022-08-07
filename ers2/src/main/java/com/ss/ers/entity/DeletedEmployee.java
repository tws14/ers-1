package com.ss.ers.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name = "backup_employee")
@Data
public class DeletedEmployee {
    
    @Id
    @Column (name = "b_id")
    private Integer id;
    
    @Column (name = "b_last_name")
    private String lastName;
    
    @Column (name = "b_first_name")
    private String firstName;
    
    @Column (name = "b_gender")
    private String gender;
    
    @Column (name = "b_dept")
    private String dept;
    
    @Column (name = "b_birth")
    private LocalDate birth;
    
    @Column (name = "b_email")
    private String email;
    
    @Column (name = "b_tel_no")
    private String tel;
    
    @Column (name = "b_zip")
    private String zipcode;
    
    @Column (name = "b_address1")
    private String address1;
    
    @Column (name = "b_address2")
    private String address2;
    
    @Column (name = "b_address3")
    private String address3;
    
    @Column (name = "b_school1")
    private String school1;
    
    @Column (name = "b_school2")
    private String school2;
    
    @Column (name = "b_skill")
    private String skill;
    
    @Column (name = "b_image")
    private String image;
    
    @Column (name = "dt")
    private LocalDateTime dateTime;
    
}
