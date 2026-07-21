package com.example.ParkingTask.Repository;

import com.example.ParkingTask.Entity.tariffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface tariffRepo extends JpaRepository<tariffEntity,Integer> {


    Optional<tariffEntity> findById(Integer Id);
    Optional<tariffEntity> findByDuration(Integer duration);

}
