package com.yash.service;

import com.yash.entity.User;
import com.yash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),new ArrayList<>());
        return userDetails;
    }
}
