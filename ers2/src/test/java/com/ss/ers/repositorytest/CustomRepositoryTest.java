package com.ss.ers.repositorytest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ss.ers.dao.EmployeeRepositoryCustom;
import com.ss.ers.entity.Employee;


@SpringBootTest
class CustomRepositoryTest {
    
    @Autowired
    private EmployeeRepositoryCustom employeeRepositoryCustom;
    
    @Test
    @DisplayName("アンド検索チェック")
    void test () {
        
        
        //1 lastname 2 firstname 3 gender 4dept 5 birth 6 email 
        //7 tel 8 zipcode 9 ad1 10 ad2 11ad3 12 school1 13school2 14skill
        
        List<Employee> testList1 = employeeRepositoryCustom.andSearch ("山", "", "", "", null, "", "","", "", "", "", "", "", "");
      // error のためコメントアウトー＞サービスクラスでチェック   List<Employee> testList2 = employeeRepositoryCustom.andSearch ("", "", "","", null, "", "", "", "", "", "", "", "", "");
        
        assertEquals (4, testList1.size ());
        //assertEquals (0, testList2.size ());
        
        
    }
    
}
