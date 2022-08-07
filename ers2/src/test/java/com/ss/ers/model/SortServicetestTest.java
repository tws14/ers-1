package com.ss.ers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SortServicetestTest {
    
    @Autowired
    SortServiceTest serviceTest;
    
    @Test
    void test () {
     
        serviceTest.sortTest ();
        
    }
    
}
