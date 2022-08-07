package com.ss.ers.service.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ss.ers.dao.LogInfoRepository;
import com.ss.ers.entity.LogInfo;
import com.ss.ers.service.LogService;

@Service
@Transactional
public class LogServiceImpl implements LogService{
    
    
    @Autowired
    private LogInfoRepository logInfoRepository;
    

    @Override
    public void save (LogInfo logInfo) {
      logInfoRepository.save (logInfo);
    }

    @Override
    public List<LogInfo> findAllOrderByLogNoDesc () {

        return logInfoRepository.findAllByOrderByLogNoDesc ();
    }

    @Override
    public void deleteByLogNo (Integer logNo) {
       logInfoRepository.deleteByLogNo (logNo);
    }
    
    
    
    
    
}
