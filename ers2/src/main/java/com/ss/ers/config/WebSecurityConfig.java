package com.ss.ers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder ();
    }
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    public void configure (WebSecurity web) throws Exception {
        web.ignoring ().antMatchers ("/css/**").antMatchers ("/js/**").antMatchers ("/images/**");
    }
    
    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.authorizeRequests ().antMatchers ("/loginfo/**").hasRole ("ADMIN").antMatchers ("/backup/**")
                .hasAnyRole ("ADMIN").antMatchers ("/**").authenticated ().and ().formLogin ().loginPage ("/loginform")
                .loginProcessingUrl ("/login").usernameParameter ("email").passwordParameter ("password")
                .defaultSuccessUrl ("/emp/list", true).failureUrl ("/loginform?error=true").permitAll ();
        
        // http.csrf ().disable ();
    }
    
    @Override
    public void configure (AuthenticationManagerBuilder auth) throws Exception {
        /*
         * auth.inMemoryAuthentication () .withUser ("user").password (passwordEncoder
         * ().encode ("user")).roles("USERS");
         **/
        auth.userDetailsService (userDetailsService).passwordEncoder (passwordEncoder ());
    }
    
}
