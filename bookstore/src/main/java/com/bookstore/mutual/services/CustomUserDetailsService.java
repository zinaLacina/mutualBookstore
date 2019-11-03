package com.bookstore.mutual.services;

import com.bookstore.mutual.domain.UserBookstore;
import com.bookstore.mutual.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserBookstore user = userRepository.findByUsernameEqualsIgnoreCase(username).get();
        if(user==null) new UsernameNotFoundException("User not found");
        return user;
    }


    @Transactional
    public UserBookstore loadUserById(String id){
        UserBookstore user = userRepository.findById(id).get();
        if(user==null) new UsernameNotFoundException("User not found");
        return user;

    }
}
