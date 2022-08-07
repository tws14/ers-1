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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.ss.ers.controller.EmployeeController;
import com.ss.ers.entity.Employee;
import com.ss.ers.service.EmployeeService;


@SpringBootTest
class SearchAllTest {
    
    private MockMvc mockMvc;
    
    @MockBean
    EmployeeService employeeService;
    
    @InjectMocks
    EmployeeController employeeController;
    
    @BeforeEach
    void setup() {
        
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        // viewResolver.setPrefix("");
         viewResolver.setSuffix(".html");
    
        
        MockitoAnnotations.openMocks (this);
        mockMvc = MockMvcBuilders.standaloneSetup (employeeController).setViewResolvers (viewResolver).build ();
        }
    
    
    @Test
    @DisplayName("全文検索")
    void searchAllTest () throws Exception {
        
        LocalDate test1 = LocalDate.parse ("2001-01-01",DateTimeFormatter.ofPattern ("yyyy-MM-dd"));
        
        
        List<Employee> list1 = new ArrayList<>();
        Employee e1 = Employee.builder ().lastName ("a").firstName ("a").gender ("男").dept ("G1")
                                                                    .birth (test1).email ("aa@aa").tel ("1010001222").zipcode ("1001000")
                                                                    .address1 ("a").address2 ("a").address3 ("a").school1 ("a").school2 ("a").skill ("a").build ();
                
        Employee e2 = Employee.builder ().lastName ("b").firstName ("b").gender ("男").dept ("b")
                .birth (test1).email ("bb@bb").tel ("1010001222").zipcode ("1001000")
                .address1 ("b").address2 ("b").address3 ("b").school1 ("b").school2 ("b").skill ("b").build ();

       
        list1.add (e1);
        list1.add (e2);
        
        Mockito.when (employeeService.findAll ()).thenReturn (list1);
        
        MvcResult result = mockMvc.perform (get("/emp/searchAll").param("searchall", "b"))
                                                                            .andExpect(status().isOk())
                                                                            .andExpect(view().name("/emp/employeeTable :: employeeTable")).andReturn();
        
                  @SuppressWarnings ("unchecked")
                List<Employee> list2 = (List<Employee>) result.getModelAndView ().getModel ().get ("employees");
                  
                  
                  String a = list2.get (0).getLastName ();
                  
                                                                         
        assertEquals (a,  "b");

        
        System.out.println ("name : " + a);
        
        
        
        }
    
}
