package com.ss.ers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ss.ers.model.AndSearchLogic;

class AndSearchTest {
    
    AndSearchLogic andSearchLogic;
    
    String[] str = {"山田", "太郎",  "男", "G1", "2000-01-01", "aaa@co.jp"
            ,"09049498989", "0100101", "東京", "３－３", "マンション"
            , "学校", "学科", "特技"};
    
    String[] str2 = {"山田", "",  "男", "G1", "2000-01-01", "aaa@co.jp"
            ,"09049498989", "0100101", "東京", "３－３", "マンション"
            , "学校", "学科", "特技"};
    
    String[] str3 = {"", "",  null,  "",  null, ""
            ,"", "", "", "", ""
            , "", "", ""};
    
    @BeforeEach
    void testbeforeeach() {
        AndSearchLogic andSearchLogic = new AndSearchLogic ();
        this.andSearchLogic = andSearchLogic;
    }
    
    @Test
    @DisplayName("フォームチェックテスト")
    void formCheckTest () {
       
        
        boolean a = andSearchLogic.formCheck (str);
        boolean b  = andSearchLogic.formCheck (str2);
        boolean c = andSearchLogic.formCheck (str3);
        
        assertTrue (a, "trueを返します");
        assertTrue(b, "trueを返します");
        assertFalse(c, "falseを返します");
        
    }
    
    @Test
    @DisplayName("フォームカラムチェックテスト")
    void formColumnCheckTest() {
        
        boolean d = andSearchLogic.formColumnCheck (true, str3);
        boolean e = andSearchLogic.formColumnCheck (false, str3);
        
        
        assertTrue (d, "trueを返します");
        assertFalse(e, "falseを返します");
        
    }
    
    @Test
    @DisplayName("フォームカラム　両方")
    void formAndColumn() {
        
        boolean f = andSearchLogic.formColumnCheck (andSearchLogic.formCheck (str), str);
        
        boolean g = andSearchLogic.formColumnCheck (andSearchLogic.formCheck (str2), str2);
        
        boolean h = andSearchLogic.formColumnCheck (andSearchLogic.formCheck (str3), str3);
        
        assertTrue (f);        
        assertTrue (g);        
        assertFalse (h);
        
        
    }
    
    
    
}
