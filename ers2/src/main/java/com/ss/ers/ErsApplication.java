package com.ss.ers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ErsApplication implements CommandLineRunner {
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
	public static void main(String[] args) {
		SpringApplication.run(ErsApplication.class, args);
	}



    @Override
    public void run (String... args) throws Exception {
        String password = "test123";
        String digest = passwordEncoder.encode(password);
        System.out.println("ハッシュ値 = " + digest);

 
}

    }
