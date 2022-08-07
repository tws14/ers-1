package com.ss.ers.controllertest.employeeController;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.ss.ers.controller.EmployeeController;
import com.ss.ers.entity.Employee;
import com.ss.ers.form.RegisterForm;
import com.ss.ers.service.EmployeeService;
import com.ss.ers.service.EmployeeServiceCustom;

@SpringBootTest
class AndSearchControllerTest {
    
    private MockMvc mockMvc;
    
    @MockBean
    EmployeeServiceCustom employeeServiceCustom;
    
    @MockBean
    EmployeeService employeeService;
    
    @Spy
    ModelMapper modelMapper = new ModelMapper ();
    
    @InjectMocks
    EmployeeController employeeController;
    
 /*   @ModelAttribute
    public RegisterForm setupForm() {
        RegisterForm form = new RegisterForm();
        return form;
    } */
    
    
    @BeforeEach
    void setup() {
        

        MockitoAnnotations.openMocks (this);
        mockMvc = MockMvcBuilders.standaloneSetup (employeeController).build ();
    }
   
    
    @Test
    void test () throws Exception {
        
        LocalDate test1 = LocalDate.parse ("2001-01-01", DateTimeFormatter.ofPattern ("yyyy-MM-dd"));
        
        RegisterForm form = new RegisterForm ();
        form.setLastName ("");
        form.setFirstName ("");
        form.setGender (null);
        form.setDept ("");
        form.setBirth (null);
        form.setEmail ("");
        form.setTel ("");
        form.setZipcode ("");
        form.setAddress1 ("");
        form.setAddress2 ("");
        form.setAddress3 ("");
        form.setSchool1 ("");
        form.setSchool2 ("");
        form.setSkill ("");
        
        
        
        
       
        List<Employee>employees1 = new ArrayList<> ();
        Employee employee1 = new Employee();
        employee1.setLastName ("aaa");
        employees1.add (employee1);
        
      Mockito.when (employeeService.findAll ()).thenReturn (employees1);
        
      
      
        Mockito.when(employeeServiceCustom.andSearch ("aaa", "aaa", "男", "G1", test1, "aaa@aaa", "0900900903", "1234567", "aa", "aa", "aa", "aa", "aa", "aa"))
        .thenReturn (employees1);
             
        
        MvcResult result = mockMvc.perform (get ("/emp/andSearch").flashAttr ("registerForm",form)).andExpect (status ().isOk ())
                .andExpect (view ().name ("/emp/employeeTable :: employeeTable")).andReturn ();
        
       /* param ("lastName", "aaa").param ("firstName", "aaa").param ("gender", "男")
        .param ("dept", "G1").param ("birth","2001-01-01").param ("email", "aaa@aaa").param ("tel", "0900900903").param ("zipcode", "1234567").param ("address1", "aa").param ("address2", "aa")
        .param ("address3", "aa").param ("school1", "aa").param ("school1", "aa").param ("school2", "aa").param ("skill", "aa"). */
        
        @SuppressWarnings ("unchecked")
        List<Employee> employees2 = (List<Employee>) result.getModelAndView ().getModel ().get("employees");
        
        String a = employees1.get (0).getLastName ();
        int b = employees2.size ();
        
        assertEquals (a, "aaa");
        System.out.println (b);
    }
    
}
