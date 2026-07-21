package com.example.ParkingTask.Initializer;

import com.example.ParkingTask.Entity.UserEntity;
import com.example.ParkingTask.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultAdminInitializer implements CommandLineRunner {

    @Autowired
    private UserRepo userRepo;
    @Autowired
   private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {

        if(userRepo.findByrole("ADMIN").isEmpty()){

            UserEntity admin = new UserEntity();

            admin.setUserName("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");

            userRepo.save(admin);

            System.out.println("Default admin created.");
        }
    }
}
