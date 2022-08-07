package com.ss.ers.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ss.ers.dao.BackupRepository;
import com.ss.ers.dao.BackupRepositoryCustom;
import com.ss.ers.entity.DeletedEmployee;
import com.ss.ers.entity.Employee;
import com.ss.ers.service.EmployeeService;

@Service
@Transactional
public class BackupService {
    
    @Autowired
    private BackupRepository backupRepository;
    
    @Autowired
    private BackupRepositoryCustom backupRepositoryCustom;
    
    @Autowired
    private EmployeeService employeeService;
    
    public List<DeletedEmployee> findAll () {
        
        List<DeletedEmployee> dle = backupRepository.findAll ();
        
        return dle;
    }
    
    public Employee reverseEmployee (Integer id) {
        
        Optional<DeletedEmployee> option = backupRepository.findById (id);
        
        DeletedEmployee de = option.orElse (null);
        
        if (de != null) {
            
            Employee employee = Employee.builder ().id (de.getId ()).lastName (de.getLastName ())
                    .firstName (de.getFirstName ()).gender (de.getGender ()).dept (de.getDept ()).birth (de.getBirth ())
                    .email (de.getEmail ()).tel (de.getTel ()).zipcode (de.getZipcode ()).address1 (de.getAddress1 ())
                    .address2 (de.getAddress2 ()).address3 (de.getAddress3 ()).school1 (de.getSchool1 ())
                    .school2 (de.getSchool2 ()).skill (de.getSkill ()).image ("C:\\img\\profile\\syoki.png").build ();
            
            employeeService.save (employee);
            
            backupRepository.deleteById (id);
            
            return employee;
        }
        
        return null;
    }
    
    public void deleteEmployeeTruncate () {
        
        backupRepositoryCustom.deleteEmployeeTruncate ();
        
    }
    
    /*
     * public DeletedEmployee findAlltest () {
     * 
     * List<DeletedEmployee> dle = backupRepository.findAll ();
     * 
     * DeletedEmployee deletedEmployee = dle.get (0);
     * 
     * return deletedEmployee; }
     * 
     * public DeletedEmployee findAlltest (String lastName) {
     * 
     * return null; }
     */
    
}
