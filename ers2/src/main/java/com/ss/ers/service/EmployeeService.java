package com.ss.ers.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.ss.ers.entity.Employee;


public interface EmployeeService {
    

    public void save (Employee employee);
    
    public void update (Employee employee) throws IllegalStateException, IOException;
    
    public List<Employee> findAll ();
    
    public Optional<Employee> findById (Integer id);
    
    public Employee findByEmail(String Email);
    
    public void deleteById (Integer id);
    
}
