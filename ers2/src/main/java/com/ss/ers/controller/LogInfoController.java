package com.ss.ers.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ss.ers.entity.LogInfo;
import com.ss.ers.service.LogService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/loginfo")
@Slf4j
public class LogInfoController {

    @Autowired
    private LogService logService;
    
    
    @GetMapping("/loglist")
    public String getLogList(Model model) {
        
       List<LogInfo> logInfos = logService.findAllOrderByLogNoDesc ();
       
      List<LogInfo> logInfos2 = new ArrayList<>(logInfos);
      
     Collections.reverse (logInfos2);
       
     if(logInfos.size () > 30) {
     
             for(LogInfo templog : logInfos2) {
         
                     Integer tempNo = templog.getLogNo ();
         
                     //ログ削除
                     logService.deleteByLogNo (tempNo);
         
                     //Listから削除
                     logInfos.remove (logInfos.size () -1);
                     
                     if(logInfos.size () == 30 ) {
                         break;
                     }
                }
         
     }
       
       LogInfo logInfo = logInfos.get (0);
       
       String aaaa = logInfo.getName ();
       
       log.info(aaaa);
       
       model.addAttribute ("logInfos",logInfos);
        
        return "/loginfo/loglist";
    }

}
