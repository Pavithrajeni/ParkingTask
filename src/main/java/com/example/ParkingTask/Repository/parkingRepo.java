package com.example.ParkingTask.Repository;

import com.example.ParkingTask.Entity.parkingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface parkingRepo extends JpaRepository<parkingEntity, Integer> {

Optional<parkingEntity> findByVehicleNumber(Integer vehicleNumber);


}
