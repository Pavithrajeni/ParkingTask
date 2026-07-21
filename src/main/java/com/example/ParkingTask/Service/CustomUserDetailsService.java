package com.example.ParkingTask.Service;

import com.example.ParkingTask.Entity.UserEntity;
import com.example.ParkingTask.Exception.UsernotFoundExcception;
import com.example.ParkingTask.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserEntity userEntity= userRepo.findByUserName(username).orElseThrow(()-> new UsernotFoundExcception("the userName is not found"));
       UserDetails usd= User.withUsername(userEntity.getUserName())
               .password(userEntity.getPassword())
               .roles(userEntity.getRole())
               .build();

       return usd;
    }
}
