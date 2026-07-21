package com.example.ParkingTask.Controller;

import com.example.ParkingTask.Pojo.UserPojo;
import com.example.ParkingTask.Service.userService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
 private userService userService;
    @PostMapping("/Register")
    public String newUser(
            @RequestBody UserPojo userPojo){

    return userService.RegisterUser(userPojo);
    }
    @PutMapping("/admin/{id}")
    public String adminUser( @PathVariable Integer id){


       return userService.adminUser(id);
    }
    @PostMapping("/login")
    public String login(@RequestBody UserPojo userPojo){

       return userService.loginUser(userPojo);
    }

}
