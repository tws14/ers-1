package com.ss.ers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ss.ers.controller.BackupController;
import com.ss.ers.entity.DeletedEmployee;
import com.ss.ers.service.impl.BackupService;

@SpringBootTest
class BackupServiceTest {
    
    private MockMvc mockMvc;
    
    @MockBean
    private BackupService backupService;
    
    @InjectMocks
    private BackupController backupController;
    
    @BeforeEach
    void setup () {
        MockitoAnnotations.openMocks (this);
        mockMvc = MockMvcBuilders.standaloneSetup (backupController).build ();
    }
    
    @Test
    @DisplayName ("test")
    void test () throws Exception {
        
        DeletedEmployee deletedEmployee1 = new DeletedEmployee ();
        deletedEmployee1.setLastName ("aaa");
        // Mockito.when (backupService.findAlltest ("aaa")).thenReturn
        // (deletedEmployee1);
        
        MvcResult result = mockMvc.perform (get ("/test.html").param ("lastName", "aaa")).andExpect (status ().isOk ())
                .andExpect (view ().name ("/test")).andReturn ();
        
        DeletedEmployee deletedEmployee2 = (DeletedEmployee) result.getModelAndView ().getModel ()
                .get ("deletedEmployee");
        
        // assertEquals (deletedEmployee2.getId (), deletedEmployee1.getId ());
        assertEquals (deletedEmployee2.getLastName (), deletedEmployee1.getLastName ());
        System.out.println (deletedEmployee1.getLastName ());
    }
    
}
