package com.ss.ers.dao;

import java.time.LocalDate;
import java.util.List;

import com.ss.ers.entity.Employee;

public interface EmployeeRepositoryCustom {
    
    public List<Employee> andSearch(String lastName,  String firstName, String gender,
                                                            String dept, LocalDate birth, String email, String tel,
                                                            String zipcode, String address1, String address2, String address3,
                                                            String schoo1, String school2, String skill) ;
    
}
