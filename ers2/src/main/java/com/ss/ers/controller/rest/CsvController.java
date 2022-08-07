package com.ss.ers.controller.rest;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.ss.ers.entity.Csv;
import com.ss.ers.entity.Employee;
import com.ss.ers.service.EmployeeService;

@RestController
@RequestMapping("/csv")
public class CsvController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @RequestMapping( value = "/employee.csv", 
                produces= MediaType.APPLICATION_OCTET_STREAM_VALUE 
                + "; charset=UTF-8; Content-Disposition: attachment")
    public Object csvDownload() throws JsonProcessingException {
        // マッパー
        CsvMapper mapper = new CsvMapper();
        //スキーマをつくる
        CsvSchema schema = mapper.schemaFor (Csv.class).withHeader ();
        
        List<Csv> csvs = new ArrayList<Csv>();
        
        List<Employee> tempEmployees = employeeService.findAll ();
        
        for (Employee e : tempEmployees) {
            
            String tempDate = e.getBirth ().format (DateTimeFormatter.ofPattern ("yyyy-MM-dd"));
            
            Csv csv = new Csv(e.getLastName (), e.getFirstName (), e.getGender (), 
                                            e.getDept (), tempDate, e.getEmail (), e.getTel (),
                                            e.getZipcode (), e.getAddress1 (), e.getAddress2 (),
                                            e.getAddress3 (), e.getSchool1 (), e.getSchool2 (),
                                            e.getSkill ());
            
            csvs.add (csv);
            
        }
        
        return mapper.writer(schema).writeValueAsString (csvs);
    }
}
