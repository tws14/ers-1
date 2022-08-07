package com.ss.ers.service.impl;

import java.time.LocalDate;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ss.ers.dao.EmployeeRepository;
import com.ss.ers.dao.EmployeeRepositoryCustom;
import com.ss.ers.entity.Employee;
import com.ss.ers.service.EmployeeServiceCustom;


@Service
@Transactional
public class EmployeeServiceCustomImpl implements EmployeeServiceCustom {
    
    @Autowired
    private EmployeeRepositoryCustom  employeeRepositoryCustom;
    
    @Override
    public List<Employee> andSearch (String lastName, String firstName, String gender, String dept, LocalDate birth,
            String email, String tel, String zipcode, String address1, String address2, String address3, String schoo1,
            String school2, String skill) {
       
        
        
        
        return employeeRepositoryCustom.andSearch (lastName, firstName, gender, dept, birth, email,
                                                                                            tel, zipcode, address1, address2, address3, schoo1, school2, skill);
    }
    
}
