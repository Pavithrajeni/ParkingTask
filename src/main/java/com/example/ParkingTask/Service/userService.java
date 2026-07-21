package com.example.ParkingTask.Service;

import com.example.ParkingTask.Entity.UserEntity;
import com.example.ParkingTask.Exception.UserAlreadyExistException;
import com.example.ParkingTask.Exception.UsernotFoundExcception;
import com.example.ParkingTask.Pojo.UserPojo;
import com.example.ParkingTask.Repository.UserRepo;
import com.example.ParkingTask.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    public String RegisterUser(UserPojo userPojo){

       if( userRepo.findByUserName(userPojo.getUserName()).isPresent()){
           throw new UserAlreadyExistException("user is already resgisterd"+userPojo.getUserName());
       }

        UserEntity entity=new UserEntity();
       entity.setUserName(userPojo.getUserName());
       entity.setPassword(passwordEncoder.encode(userPojo.getPassword()));
       entity.setRole("USER");
       userRepo.save(entity);
       return "New User is registered successfully ";

    }


    public String adminUser(Integer id){

       UserEntity entity= userRepo.findByuserId(id).orElseThrow(() ->new UsernotFoundExcception("userid doesnt exist"));
entity.setRole("ADMIN");
userRepo.save(entity);
return "role has been setted ";
    }

    public String loginUser(UserPojo userPojo){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userPojo.getUserName(),userPojo.getPassword()));
        return jwtUtil.generateToken(userPojo.getUserName());
    }
}
