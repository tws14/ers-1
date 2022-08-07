package com.ss.ers.service;

import java.util.List;

import com.ss.ers.entity.LogInfo;

public interface LogService {
    
    
    public List<LogInfo> findAllOrderByLogNoDesc();
    
    
    public void save(LogInfo logInfo);

    
    public void deleteByLogNo(Integer logNo);

}
