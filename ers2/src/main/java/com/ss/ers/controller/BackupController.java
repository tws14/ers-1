package com.ss.ers.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ss.ers.entity.DeletedEmployee;
import com.ss.ers.entity.Employee;
import com.ss.ers.entity.LogInfo;
import com.ss.ers.entity.domain.UserDetailsImpl;
import com.ss.ers.service.LogService;
import com.ss.ers.service.impl.BackupService;

@Controller
@RequestMapping ("/backup")
public class BackupController {
    
    @Autowired
    private BackupService backupService;
    
    @Autowired
    private LogService logService;
    
    @RequestMapping ("/list")
    public String test (Model model) {
        
        List<DeletedEmployee> deletedEmployees = backupService.findAll ();
        
        model.addAttribute ("deletedEmployees", deletedEmployees);
        
        return "/loginfo/backup";
    }
    
    @PostMapping ("/reverse/{id}")
    public String reverseEmployee (@PathVariable ("id") Integer id, Model model,
            @AuthenticationPrincipal UserDetailsImpl user) {
        
        Employee delEmployee = backupService.reverseEmployee (id);
        
        LogInfo logInfo = LogInfo.builder ().name (user.getUsername ()).operation ("reverse")
                .target (delEmployee.getLastName () + " " + delEmployee.getFirstName ()).result ("success")
                .date (LocalDate.now ()).build ();
        
        logService.save (logInfo);
        
        return "redirect:/backup/list";
    }
    
    @PostMapping ("/truncate")
    public String deleteEmployeeTruncate (@AuthenticationPrincipal UserDetailsImpl user) {
        
        backupService.deleteEmployeeTruncate ();
        
        LogInfo logInfo = LogInfo.builder ().name (user.getUsername ()).operation ("truncate")
                .target ("deletedEmployee").result ("success").date (LocalDate.now ()).build ();
        
        logService.save (logInfo);
        
        return "redirect:/backup/list";
    }
    
    /*
     * @GetMapping ("/test") テスト public String test (@RequestParam (value =
     * "lastName", required = false) String lastName, Model model) {
     * 
     * DeletedEmployee employee = backupService.findAlltest (lastName);
     * 
     * model.addAttribute ("deletedEmployee", employee);
     * 
     * return "/test"; }
     */
    
    @ExceptionHandler (SQLIntegrityConstraintViolationException.class)
    public String sqlIntegrityE (SQLIntegrityConstraintViolationException e, Model model,
            @AuthenticationPrincipal UserDetailsImpl user) {
        
        LogInfo logInfo = LogInfo.builder ().name (user.getUsername ()).operation ("Registration(CSV)")
                .target ("CSVrow").result ("EmailError").date (LocalDate.now ()).build ();
        
        logService.save (logInfo);
        
        // 例外クラスのメッセージをModelに登録
        model.addAttribute ("error", "内部サーバーエラー（DB）：ExceptionHandler");
        
        // 例外クラスのメッセージをModelに登録
        model.addAttribute ("message", "SQLIntegrityConstraintViolationExceptionが発生しました。"
                + "　既に登録されているアドレスがあり、重複NGのためエラーが発生しました。" + "　確認してください。");
        
        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute ("status", HttpStatus.INTERNAL_SERVER_ERROR);
        
        return "error";
    }
}
