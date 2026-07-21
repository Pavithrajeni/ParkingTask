package com.example.ParkingTask.Service;

import com.example.ParkingTask.Entity.parkingEntity;
import com.example.ParkingTask.Exception.DuplicateVehicleException;
import com.example.ParkingTask.Exception.VehicleNotFoundException;
import com.example.ParkingTask.Pojo.parkingPojo;
import com.example.ParkingTask.Repository.parkingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.ParkingTask.DTO.ParkingDto;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class parkingService {

    @Autowired
   private parkingRepo parkingRepo;
    @Autowired
    private tariffService tariffService;
    private static final Logger logger =
            LoggerFactory.getLogger(parkingService.class);

    public String regsiterService(parkingPojo parkingPojo) {

if(parkingRepo.findByVehicleNumber(parkingPojo.getVehicleNumber()).isPresent()) {
    throw new DuplicateVehicleException("vehcile already present in the number of " + parkingPojo.getVehicleNumber());
}
//        List<parkingEntity> parkings = parkingRepo.findAll();
//
//        for (parkingEntity exsitingVehicle : parkings) {
//
//            if (exsitingVehicle.getVehicleNumber().equals(parkingPojo.getVehicleNumber())
//            ) {
//                throw new DuplicateVehicleException("vehcile already present in the number of " + parkingPojo.getVehicleNumber());
//            }
//        }

            parkingEntity park = new parkingEntity();

            park.setVehicleNumber(parkingPojo.getVehicleNumber());
            park.setVehicleType(parkingPojo.getVehicleType());
            park.setVehicleColor(parkingPojo.getVehicleColor());
            park.setVehicleBrand(parkingPojo.getVehicleBrand());
            park.setTimeStamp(LocalDateTime.now());
            park.setStatus(parkingEntity.VehicleStatus.ACTIVE);

            parkingRepo.save(park);
        logger.info("Vehicle Registered Successfully : {}",
                parkingPojo.getVehicleNumber());
            return "Vehicle saved successfully " + parkingPojo.getVehicleNumber();

        }



    public  ParkingDto fetchVehicleId(Integer vehicleId){

      parkingEntity entity= parkingRepo.findById(vehicleId).orElseThrow(() ->new VehicleNotFoundException("vehicle not found"+ vehicleId));
        ParkingDto parkingDto = new ParkingDto();
        parkingDto.setVehicleNumber(entity.getVehicleNumber());
        parkingDto.setVehicleBrand(entity.getVehicleBrand());
        parkingDto.setVehicleColor(entity.getVehicleColor());
        parkingDto.setVehicleType(entity.getVehicleType());
        parkingDto.setStatus(entity.getStatus());
        logger.info("Vehicle fetched : {}", vehicleId);
            return parkingDto;



        }

public ParkingDto exitVehicle(int vehicleId){
        //vehicle checking
         parkingEntity exitEntity=   parkingRepo.findById(vehicleId).orElseThrow(()-> new VehicleNotFoundException("vehicle not found"+ vehicleId) );
         //status checking
    if(exitEntity.getStatus()== parkingEntity.VehicleStatus.EXITED){
        throw new RuntimeException("Vehicle already exited");
    }

//exit time setting
    exitEntity.setExitTime(LocalDateTime.now());

//calculation with predefined class in java  --Duration(takes exit time an dentrytime and calculates

    Duration duration=Duration.between(exitEntity.getTimeStamp(),exitEntity.getExitTime());

    long hours=duration.toHours();
    if(hours==0){
        hours=1;
    }

    //fee for 1 hr is 20rs
//Long fee=hours*20;
    long fee= tariffService.getAmountByDuration((int)hours);
exitEntity.setStatus(parkingEntity.VehicleStatus.EXITED);
    parkingRepo.save(exitEntity);

    ParkingDto parkingDto = new ParkingDto();
    parkingDto.setVehicleNumber(exitEntity.getVehicleNumber());
    parkingDto.setVehicleBrand(exitEntity.getVehicleBrand());
    parkingDto.setVehicleColor(exitEntity.getVehicleColor());
    parkingDto.setVehicleType(exitEntity.getVehicleType());
    parkingDto.setStatus(exitEntity.getStatus());
    parkingDto.setFee(fee);


    logger.info("Vehicle exited : {}", vehicleId);

    return parkingDto;

}

    }
