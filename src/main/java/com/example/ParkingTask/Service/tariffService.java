package com.example.ParkingTask.Service;

import com.example.ParkingTask.Entity.tariffEntity;
import com.example.ParkingTask.Pojo.tariffPojo;
import com.example.ParkingTask.Repository.tariffRepo;
//import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class tariffService {
    @Autowired
    private tariffRepo traiffRepo;
    private static final Logger logger =
            LoggerFactory.getLogger(tariffService.class);


   public String addTariff(tariffPojo tariffPojo){

      if(traiffRepo.findByDuration(tariffPojo.getDuration()).isPresent()){
           throw new RuntimeException("already thsi duration is present ");
       }
       tariffEntity entity= new tariffEntity();
        entity.setId(tariffPojo.getId());
       entity.setDuration(tariffPojo.getDuration());
       entity.setAmount(tariffPojo.getAmount());
      traiffRepo.save(entity);
       logger.info("Tariff Added : Duration {} Amount {}",
               tariffPojo.getDuration(),
               tariffPojo.getAmount());
       return "succesffuly added "+entity;


   }

    public String updateTraiff(Integer id, tariffPojo tariffPojo ){

//        List<tariffEntity> tariffs=traiffRepo.findAll();
//
//        for(tariffEntity tariff:tariffs)
//        {
//            if(tariff.getId().equals(id.getId())){
//                tariff.setDuration(id.getDuration());
//                tariff.setAmount(id.getAmount());
//                traiffRepo.save(tariff);
//                return "succesfully updated "+id.getId();
//            }
//
//        }
//
//
//          throw new RuntimeException("no id is found ");

      tariffEntity entity=  traiffRepo.findById(id).orElseThrow(() -> new RuntimeException("no id is found"));


          entity.setDuration(tariffPojo.getDuration());
          entity.setAmount(tariffPojo.getAmount());
          traiffRepo.save(entity);
        logger.info("Tariff Updated : {}", id);
          return "successfully saved the updated data "+tariffPojo.getId();

    }


    public String deleteTraiff(Integer id) {


   tariffEntity entity=traiffRepo.findById(id).orElseThrow(()->new RuntimeException("no ID is found"));
        traiffRepo.delete(entity);
        logger.info("Tariff Deleted : {}", id);
        return "Successfully deleted tariff with ID " + id;



    }


    public Integer getAmountByDuration(Integer duration){

     tariffEntity entity=  traiffRepo.findByDuration(duration).orElseThrow(() -> new RuntimeException("no duration is found"));
     return entity.getAmount();
    }
    }

