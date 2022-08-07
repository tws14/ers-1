package com.ss.ers.dao.Impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.ers.dao.EmployeeRepositoryCustom;
import com.ss.ers.entity.Employee;

@Component
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {
    
    @Autowired
      private  EntityManager entityManager;
    
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> andSearch (String lastName, String firstName, String gender, String dept, LocalDate birth,
            String email, String tel, String zipcode, String address1, String address2, String address3, String school1,
            String school2, String skill) {
        
        StringBuilder sql = new StringBuilder ();
        
        sql.append ("SELECT e FROM Employee e WHERE ");
        boolean andFlg = false;
        boolean lastnameFlg = false;
        boolean firstnameFlg = false;
        boolean genderFlg = false;
        boolean deptFlg = false;
        boolean birthFlg = false;
        boolean emailFlg = false;
        boolean telFlg = false;
        boolean zipcodeFlg = false;
        boolean address1Flg = false;
        boolean address2Flg = false;
        boolean address3Flg = false;
        boolean school1Flg = false;
        boolean school2Flg = false;
        boolean skillFlg = false;
        
        if( !"".equals(lastName) && lastName !=null ) {
            sql.append ("e.lastName LIKE :lastName " );
            lastnameFlg = true ;
            andFlg = true;
        }
        
        if( !"".equals(firstName) && firstName != null) {
            if(andFlg) sql.append(" AND ");
            sql.append("e.firstName LIKE :firstName ");
            firstnameFlg = true;
            andFlg = true;
        }
        
        if( !"".equals(gender) && gender != null) {
            if(andFlg) sql.append(" AND ");
            sql.append("e.gender LIKE :gender ");
            genderFlg = true;
            andFlg = true;
        }
        
        if( !"".equals(dept) && dept != null) {
            if(andFlg) sql.append(" AND ");
            sql.append("e.dept LIKE :dept ");
           deptFlg = true;
            andFlg = true;
        }                
        
        if( birth != null )  {
            if(andFlg) sql.append(" AND ");
            sql.append("e.birth = :birth ");
            birthFlg = true;
            andFlg = true;
        }         
        
        if( !"".equals(email) && email  != null) {
            if(andFlg) sql.append(" AND ");
            sql.append("e.email LIKE :email ");
            emailFlg = true;
            andFlg = true;
        }        
        
        if( !"".equals(tel) && tel  != null) {
            if(andFlg) sql.append(" AND ");
            sql.append("e.tel LIKE :tel ");
            telFlg = true;
            andFlg = true;
        }        
        
        if( !"".equals(zipcode) && zipcode  != null ) {
            if(andFlg) sql.append(" AND ");
            sql.append("e.zipcode LIKE :zipcode ");
            zipcodeFlg = true;
            andFlg = true;
        }         

        if( !"".equals(address1) && address1  != null) {
            if(andFlg) sql.append(" AND ");
            sql.append("e.address1 LIKE :address1 ");
            address1Flg = true;
            andFlg = true;
        }         

        if( !"".equals(address2) && address2  != null) {
            if(andFlg) sql.append(" AND ");
            sql.append("e.address2 LIKE :address2 ");
            address2Flg = true;
            andFlg = true;
        }         
        
        if( !"".equals(address3) && address3  != null) {
            if(andFlg) sql.append(" AND ");
            sql.append("e.address3 LIKE :address3 ");
            address3Flg = true;
            andFlg = true;
        }         
        
        if( !"".equals(school1) && school1  != null) {
            if(andFlg) sql.append(" AND ");
            sql.append("e.school1 LIKE :school1 ");
            school1Flg = true;
            andFlg = true;
        }         
        
        if( !"".equals(school2) && school2  != null) {
            if(andFlg) sql.append(" AND ");
            sql.append("e.school2 LIKE :school2 ");
            school2Flg = true;
            andFlg = true;
        }       

        
        if( !"".equals(skill) && skill  != null) {
            if(andFlg) sql.append(" AND ");
            sql.append("e.skill LIKE :skill ");
            skillFlg = true;
            andFlg = true;
        }        
        
       Query query = entityManager.createQuery (sql.toString ());

       if (lastnameFlg) query.setParameter ("lastName", "%" + lastName + "%");
       if(firstnameFlg) query.setParameter ("firstName", "%" + firstName + "%");
       if(genderFlg) query.setParameter ("gender", gender);
       if(deptFlg) query.setParameter ("dept", dept);
       if(birthFlg) query.setParameter ("birth", birth);
       if(emailFlg) query.setParameter ("email", "%" + email + "%");
       if(telFlg) query.setParameter ("tel", "%" + tel + "%");
       if(zipcodeFlg) query.setParameter ("zipcode", "%" + zipcode + "%");
       if(address1Flg) query.setParameter ("address1", "%" + address1 + "%");
       if(address2Flg) query.setParameter ("address2", "%" + address2 + "%");
       if(address3Flg) query.setParameter ("address3", "%" + address3 + "%");
       if(school1Flg) query.setParameter ("school1", "%" + school1 + "%");
       if(school2Flg) query.setParameter ("school2", "%" + school2 + "%");
       if(skillFlg) query.setParameter ("skill", "%" + skill + "%"); 
       
        return query.getResultList ();
    }
    
}
