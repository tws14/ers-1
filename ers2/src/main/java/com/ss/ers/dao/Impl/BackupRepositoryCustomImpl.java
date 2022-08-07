package com.ss.ers.dao.Impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.ers.dao.BackupRepositoryCustom;

@Component
public class BackupRepositoryCustomImpl implements BackupRepositoryCustom {
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public void deleteEmployeeTruncate () {
        
        String sql = "TRUNCATE TABLE backup_employee";
        
        Query query = entityManager.createNativeQuery (sql);
        
        query.executeUpdate ();
        
    }
    
}
