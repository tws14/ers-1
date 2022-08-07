package com.ss.ers.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ss.ers.dao.EmployeeRepository;
import com.ss.ers.entity.Employee;

@Service
public class SortServiceTest {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    
    public void sortTest() {
        
        List<Employee> employees = employeeRepository.findAll (Sort.by(Sort.Direction.DESC ,"lastName"));
        
        for(Employee employee : employees) {
            
            System.out.println (employee.toString ());
            
            
        }
        
    }
    
}
