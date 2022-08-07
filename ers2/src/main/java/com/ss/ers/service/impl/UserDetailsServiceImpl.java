package com.ss.ers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ss.ers.dao.UserRepository;
import com.ss.ers.entity.domain.DomainUser;
import com.ss.ers.entity.domain.UserDetailsImpl;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    
    
    @Autowired
    private UserRepository userRepository;
    
    
    @Override
    public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
        DomainUser user = userRepository.findByEmail (email);
        if(user == null ) {
            throw new UsernameNotFoundException ("user is null");
        }
        return new UserDetailsImpl (user);
    }
    
}
