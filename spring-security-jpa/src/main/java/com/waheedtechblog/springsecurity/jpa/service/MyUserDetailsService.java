package com.waheedtechblog.springsecurity.jpa.service;

import com.waheedtechblog.springsecurity.jpa.dao.UserRepository;
import com.waheedtechblog.springsecurity.jpa.domain.User;
import com.waheedtechblog.springsecurity.jpa.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // load data from H2 database
        Optional<User> user = userRepository.findByUsername(username);

        // throw error if user not found in DB
        user.orElseThrow(() -> new UsernameNotFoundException("You '" + username +"' are not registered user."));

        // return userdetail service.
        return user.map(MyUserDetails::new).get();
    }
}
