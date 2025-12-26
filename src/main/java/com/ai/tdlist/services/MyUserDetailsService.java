package com.ai.tdlist.services;

import com.ai.tdlist.entities.UserEntity;
import com.ai.tdlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.ai.tdlist.dtos.User;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity= userRepository.findByUsername(username);
        if(userEntity == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new User(userEntity);
    }
}
