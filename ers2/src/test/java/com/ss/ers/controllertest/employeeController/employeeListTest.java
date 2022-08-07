package com.ss.ers.controllertest.employeeController;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.ss.ers.controller.EmployeeController;
import com.ss.ers.entity.Employee;
import com.ss.ers.service.EmployeeService;
import com.ss.ers.service.EmployeeServiceCustom;

@SpringBootTest
class employeeListTest {
    
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
    @DisplayName("社員リスト一覧メソッド")
    void test () throws Exception {
        
        List<Employee> tempEmployees = new ArrayList<>();
        Employee e = new Employee();
        e.setLastName ("e");
        tempEmployees.add (e);
        
        Mockito.when (employeeService.findAll ()).thenReturn (tempEmployees);
        
        MvcResult result = mockMvc.perform(get ("/emp/list"))
                                                                            .andExpect(status().isOk())
                                                                            .andExpect(view().name("/emp/list")).andReturn();
        
        @SuppressWarnings ("unchecked")
        List<Employee> e2 = (List<Employee>) result.getModelAndView ().getModel ().get ("employees");
        
        String a = tempEmployees.get (0).getLastName ();
        String b = e2.get (0).getLastName ();
        
        assertEquals (a, b, "同一ではありません");
        System.out.println (b);
    }
    
}
