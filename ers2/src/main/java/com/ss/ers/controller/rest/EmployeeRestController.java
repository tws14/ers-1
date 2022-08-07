package com.ss.ers.controller.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import com.ss.ers.entity.Employee;
import com.ss.ers.entity.LogInfo;
import com.ss.ers.entity.domain.UserDetailsImpl;
import com.ss.ers.service.EmployeeService;
import com.ss.ers.service.LogService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/emp/rest")
@Slf4j
public class EmployeeRestController {
    
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private LogService logService;
    
    
    
    @PostMapping ("/delete/{id}")
    public int deleteByIdFromDetail (@PathVariable ("id") Integer id,
            
            Model model, @AuthenticationPrincipal UserDetailsImpl user) {
        
        Optional<Employee> option = employeeService.findById (id);
        
        Employee delEmployee = option.orElse (null);
        
        employeeService.deleteById (id);
        
        // ログを生成
        LogInfo logInfo = LogInfo.builder ().name (user.getUsername ()).operation ("delete")
                .target (delEmployee.getLastName () + " " + delEmployee.getFirstName ()).result ("success")
                .date (LocalDate.now ()).build ();
        
        logService.save (logInfo);
        
        String id_S = String.valueOf (id);
        log.info ("削除しました" + id_S);
        log.info ("name : " + user.getUsername ());
        
        List<Employee> employees = employeeService.findAll ();
        
        model.addAttribute ("employees", employees);
        
        return 0;
        
    }
    
    
    
}


