package com.ss.ers.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ss.ers.entity.domain.DomainUser;

public interface UserRepository extends JpaRepository<DomainUser, Integer> {
    
        public DomainUser findByEmail(String email);
}
