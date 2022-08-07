package com.ss.ers.entity;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "employee")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    
 
    private static final long serialVersionUID = 6630639932135179914L;
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;
    
    @NotBlank (message = "空白は許可されていません")
    @Size (max = 20)
    @Column (name = "last_name")
    private String lastName;
    
    @NotBlank (message = "空白は許可されていません")
    @Size (max = 20)
    @Column (name = "first_name")
    private String firstName;
    
    @NotBlank
    @Size (max = 1)
    @Column (name = "gender")
    private String gender;
    
    @NotBlank
    @Column (name = "dept")
    private String dept;
    
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    @Column (name = "birth")
    private LocalDate birth;
    
    @NotBlank (message = "空白は許可されていません")
    @Email
    @Size (max = 60)
    @Column (name = "email")
    private String email;
    
    @NotBlank
    @Size (min = 10, max = 11)
    @Pattern (regexp = "^[0-9]+$")
    @Column (name = "tel_no")
    private String tel;
    
    @NotBlank
    @Size (min = 7, max = 7)
    @Pattern (regexp = "^[0-9]+$")
    @Column (name = "zip")
    private String zipcode;
    
    @NotBlank (message = "空白は許可されていません")
    @Size (max = 30)
    @Column (name = "address1")
    private String address1;
    
    @NotBlank (message = "空白は許可されていません")
    @Size (max = 30)
    @Column (name = "address2")
    private String address2;
    
    @Column (name = "address3")
    @Size (max = 30)
    private String address3;
    
    @NotBlank (message = "空白は許可されていません")
    @Size (max = 30)
    @Column (name = "school1")
    private String school1;
    
    @Size (max = 30)
    @Column (name = "school2")
    private String school2;
    
    @NotBlank (message = "空白は許可されていません")
    @Size (max = 30)
    @Column (name = "skill")
    private String skill;
    
    @Column (name = "image")
    private String image;
    
    @Transient
    private MultipartFile imageForm;
    
    public Employee (String lastName, String firstName, String gender, String dept, LocalDate birth, String email,
            String tel, String zipcode, String address1, String address2, String address3, String school1,
            String school2, String skill) {
        super ();
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.dept = dept;
        this.birth = birth;
        this.email = email;
        this.tel = tel;
        this.zipcode = zipcode;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.school1 = school1;
        this.school2 = school2;
        this.skill = skill;
    }
     
    // imageを変換
    public String encodeBase64 () {
        
        File imageFile = new File (image);
        StringBuffer base64String = new StringBuffer ();
        try {
            String contentType = Files.probeContentType (imageFile.toPath ());
            byte[] filedata = Files.readAllBytes (imageFile.toPath ());
            
            // data:contentType;base64~~の形をつくる
            base64String.append ("data:");
            base64String.append (contentType);
            base64String.append (";base64,");
            base64String.append (Base64.getEncoder ().encodeToString (filedata));
            
        } catch (IOException e) {
            // 例外処理
            e.printStackTrace ();
            
        }
        
        return base64String.toString ();
    }
    
}
