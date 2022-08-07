package com.ss.ers.model;


public class AndSearchLogic {
    
    public boolean formCheck (String[] str) {
        
        /* 1 lastname 2 firstname 3 gender 4 dept 5 birth 6 email 7 tel 8 zipcode 9 address1 10 address2 11 address3 
         *      12 school1 13 school2 14 skill**/
        
        if( "".equals(str[0]) && "".equals (str[1]) && str[2] ==null && "".equals(str[3]) && str[4] == null &&
                "".equals (str[5]) && "".equals (str[6]) && "".equals (str[7]) && "".equals (str[8]) && "".equals (str[9]) &&
                "".equals (str[10]) && "".equals (str[11]) && "".equals (str[12]) && "".equals (str[13]) ) {
            
            System.out.println ("すべての入力値が空もしくは、nullです");
            
            return false;
            
        }     
        
        System.out.println ("入力値あり");
        
        return true;
        
    }
    
    public boolean  formColumnCheck(boolean formcheck, String[] str) {
        
        if(formcheck == true) {
            
            StringBuilder sql = new StringBuilder ();
            
            sql.append ("SELECT e FROM Employee e WHERE");
            boolean andFlg = false;
            
         /*
            boolean lastnameFlag = false;
            boolean firstnameFlag = false;
            boolean genderFlag = false;
            boolean deptFlag = false;
            boolean birthFlag = false;
            boolean emailFlag = false;
            boolean telFlag = false;
            boolean zipcodeFlag = false;
            boolean address1Flag = false;
            boolean address2Flag = false;
            boolean address3Flag = false;
            boolean school1Flag = false;
            boolean school2Flag = false;
            boolean skillFlag = false; **/
            
            int SuccessCount = 0;
            
            if(!"".equals (str[0]) && str[0] != null) {
                sql.append ("e.lastName LIKE :lastName ");
              //  lastnameFlag = true;
                andFlg = true;
                SuccessCount++;
            }
            
            if(!"".equals (str[1]) && str[1] != null) {
                if(andFlg)sql.append(" AND ");
                sql.append ("e.firstName LIKE :firstName ");
             //  firstnameFlag = true;
                andFlg = true;
                SuccessCount++;
            }

            
            if(!"".equals (str[2]) && str[2] != null) {
                if(andFlg)sql.append(" AND ");
                sql.append ("e.gender LIKE :gender ");
              // genderFlag = true;
                andFlg = true;
                SuccessCount++;
            }

            
            if(!"".equals (str[3]) && str[3] != null) {
                if(andFlg)sql.append(" AND ");
                sql.append ("e.dept LIKE :dept ");
             //   deptFlag = true;
                andFlg = true;
                SuccessCount++;
            }

            
            if(str[4] != null) {
                if(andFlg)sql.append(" AND ");
                sql.append ("e.birth = :birth ");
             //   lastnameFlag = true;
                andFlg = true;
                SuccessCount++;
            }

            
            if( !"".equals(str[5]) && str[5] != null ) {
                if(andFlg)sql.append(" AND ");
                sql.append ("e.email = :emai ");
             //   lastnameFlag = true;
                andFlg = true;
                SuccessCount++;
            }

            
            if(!"".equals (str[6]) && str[6] != null) {
                if(andFlg)sql.append(" AND ");
                sql.append ("e.tel LIKE :tel ");
             //   lastnameFlag = true;
                andFlg = true;
                SuccessCount++;
            }

            
            if(!"".equals (str[7]) && str[7] != null) {
                if(andFlg)sql.append(" AND ");
                sql.append ("e.zipcode LIKE :zipcode ");
             //   lastnameFlag = true;
                andFlg = true;
                SuccessCount++;
            }

            
            if(!"".equals (str[8]) && str[8] != null) {
                if(andFlg)sql.append(" AND ");
                sql.append ("e.address1 LIKE :address1 ");
             //   lastnameFlag = true;
                andFlg = true;
                SuccessCount++;
            }

            
            if(!"".equals (str[9]) && str[9] != null) {
                if(andFlg)sql.append(" AND ");
                sql.append ("e.address2 LIKE :address2 ");
             //   lastnameFlag = true;
                andFlg = true;
                SuccessCount++;
            }
            
            if(!"".equals (str[10]) && str[10] != null) {
                if(andFlg)sql.append(" AND ");
                sql.append ("e.address3 LIKE :address3 ");
             //   lastnameFlag = true;
                andFlg = true;
                SuccessCount++;
            }

            if(!"".equals (str[11]) && str[11] != null) {
                if(andFlg)sql.append(" AND ");
                sql.append ("e.school1 LIKE :school1 ");
           //    lastnameFlag = true;
                andFlg = true;
                SuccessCount++;
            }

            if(!"".equals (str[12]) && str[12] != null) {
                if(andFlg)sql.append(" AND ");
                sql.append ("e.school2 LIKE :school2 ");
            //    lastnameFlag = true;
                andFlg = true;
                SuccessCount++;
            }
            
            if(!"".equals (str[13]) && str[13] != null) {
                if(andFlg)sql.append(" AND ");
                sql.append ("e.skill LIKE skill ");
            //    lastnameFlag = true;
                andFlg = true;
                SuccessCount++;
            }

            System.out.println (SuccessCount +" : "+ sql.toString ());
            
            return true;   
            
        } 
        
        System.out.println ("すべて空文字　null");
        
        return false;
        
    }
    
    
}
