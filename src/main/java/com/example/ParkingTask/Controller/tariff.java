package com.example.ParkingTask.Controller;

import com.example.ParkingTask.Entity.tariffEntity;
import com.example.ParkingTask.Pojo.tariffPojo;
import com.example.ParkingTask.Service.tariffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Tariff")
public class tariff {

    @Autowired
    private tariffService tariffService;

    @PostMapping("/addTariff")
    private ResponseEntity<?> addTariff(@Valid @RequestBody tariffPojo tariffPojo) {


  return ResponseEntity.ok(tariffService.addTariff(tariffPojo));
}


    @PutMapping("/update/{id}")

    private ResponseEntity<?> updateTariff(@Valid @PathVariable Integer id,@RequestBody tariffPojo tariffPojo){

       return ResponseEntity.ok(tariffService.updateTraiff(id,tariffPojo));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deleteTariff(@PathVariable Integer id){

        return ResponseEntity.ok(tariffService.deleteTraiff(id));
    }

}
