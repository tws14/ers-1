package com.ss.ers.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.ers.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

  

    public Optional<Employee> findByEmail (String email);
    
    
}
