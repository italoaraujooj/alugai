package com.alugai.demo.services;

import com.alugai.demo.models.MyUser;
import com.alugai.demo.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private MyUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(getRoles(user))
                .build();
    }

    private String[] getRoles(MyUser user){
        if(user.getRole() == null){
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }
}
