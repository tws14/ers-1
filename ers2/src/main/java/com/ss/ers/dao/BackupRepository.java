package com.ss.ers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.ers.entity.DeletedEmployee;

@Repository
public interface BackupRepository extends JpaRepository<DeletedEmployee, Integer>{
    
}
