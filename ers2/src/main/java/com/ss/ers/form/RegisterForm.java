package com.ss.ers.form;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.Data;

@Data
public class RegisterForm {
    
    private Integer id;
    
    @NotBlank(message = "空白は許可されていません")
    private String lastName;
    
    @NotBlank(message = "空白は許可されていません")
    private String firstName;
    
    private String gender;
    
    private String dept;
    
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private LocalDate birth;
    
    @NotBlank(message = "空白は許可されていません")
    private String email;
    
    private String tel;
    
    private String zipcode;
    
    @NotBlank(message = "空白は許可されていません")
    private String address1;
    
    @NotBlank(message = "空白は許可されていません")
    private String address2;
    
    private String address3;
    
    @NotBlank(message = "空白は許可されていません")
    private String school1;
    
    private String school2;
    
    @NotBlank(message = "空白は許可されていません")
    private String skill;
    
    private String image;
    
}
