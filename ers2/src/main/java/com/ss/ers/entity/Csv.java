package com.ss.ers.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"lastname", "firstname", "gender", "dept", "birth",
                                        "email", "tel", "zipcode", "address1", "address2",
                                        "address3", "school1", "school2", "skill"})
public class Csv {
    
    @JsonProperty("lastname")
    private  String lastName;
    
    @JsonProperty("firstname")
    private String firstName;
    
    @JsonProperty("gender")
    private String gender;
    
    @JsonProperty("dept")
    private String dept;
    
    @JsonProperty("birth")
    private String birth;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("tel")
    private String tel;
    
    @JsonProperty("zipcode")
    private String zipcode;
    
    @JsonProperty("address1")
    private String address1;
    
    @JsonProperty("address2")
    private String address2;
    
    @JsonProperty("address3")
    private String address3;
    
    @JsonProperty("school1")
    private String school1;
    
    @JsonProperty("school2")
    private String school2;
    
    @JsonProperty("skill")
    private String skill;
}
