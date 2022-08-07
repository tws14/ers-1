package com.ss.ers.entity.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

public class UserDetailsImpl implements UserDetails {
    

    private static final long serialVersionUID = 1L;
    
    @Getter
    private final DomainUser user;
    

    public UserDetailsImpl (DomainUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        return AuthorityUtils.createAuthorityList ("ROLE_" + this.user.getRoles ().name ());
    }
    
    @Override
    public String getPassword () {
        return this.user.getPassword ();
    }
    
    @Override
    public String getUsername () {
        return this.user.getEmail ();
    }
    
    @Override
    public boolean isAccountNonExpired () {
    
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked () {
    
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired () {

        return true;
    }
    
    @Override
    public boolean isEnabled () {
 
        return true;
    }
    

    
}
