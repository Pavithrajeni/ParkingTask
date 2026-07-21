package com.example.ParkingTask.Repository;

import com.example.ParkingTask.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Integer> {


    Optional<UserEntity> findByUserName(String userName);
    Optional<UserEntity> findByuserId(Integer userId);
    Optional<UserEntity> findByrole(String role);

}
