package com.ss.ers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.ers.entity.Employee;
import com.ss.ers.entity.LogInfo;

@Repository
public interface LogInfoRepository extends JpaRepository<LogInfo, Integer> {

       public List<LogInfo> findAllByOrderByLogNoDesc ();
       
       public void deleteByLogNo(Integer logNo);
}
