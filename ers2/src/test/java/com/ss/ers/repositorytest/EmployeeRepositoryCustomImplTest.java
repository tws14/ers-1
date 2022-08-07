package com.ss.ers.repositorytest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ss.ers.dao.Impl.EmployeeRepositoryCustomImpl;
import com.ss.ers.entity.Employee;

@SpringBootTest
class EmployeeRepositoryCustomImplTest {
    
    @Autowired
    EmployeeRepositoryCustomImpl test;
    
    @Test
    void test () {
      
     List<Employee>employees = test.andSearch ("a", "","", "", null, "", "", "", "", "", "", "", "", "");
     
     for(Employee e : employees) {
         
         System.out.println (e.toString ());
     }
        
    }
    
}
