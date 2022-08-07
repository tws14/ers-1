package com.ss.ers.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    
    @RequestMapping("loginform")
    public String getLoginForm(@AuthenticationPrincipal UserDetails user) {
        
        if(user != null) {
        
            return "redirect:/emp/list";
       
        
        } else {
            return "/loginform";
        }
    }
}
