package com.ss.ers.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="operationlog")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="log_no")
    private Integer logNo;
    
    @Column(name="ope_name")
    private String name;
    
    @Column(name="ope")
    private String operation;
    
    @Column(name="target")
    private String target;
    
    @Column(name="result")
    private String result;
    
    @Column(name="log_date")
    private LocalDate date;
}
