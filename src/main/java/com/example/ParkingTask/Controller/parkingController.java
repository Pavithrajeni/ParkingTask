package com.example.ParkingTask.Controller;

import com.example.ParkingTask.Pojo.parkingPojo;
import com.example.ParkingTask.Service.parkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/parking")
@Tag(
        name = "Parking Management",
        description = "APIs for vehicle entry, exit and parking information"
)
public class parkingController {

    @Autowired
    private parkingService parkingService;

    @Operation(
            summary = "Register a vehicle",
            description = "Registers a new vehicle when it enters the parking area."
    )
    @PostMapping("/registerVehicle")

    public ResponseEntity<?> registerVehicle(@RequestBody parkingPojo parkingPojo){
        parkingService.regsiterService(parkingPojo);
        return ResponseEntity.ok("vehicle registered successfully....");

    }

    @Operation(
            summary = "Fetch vehicle details",
            description = "Returns the details of a parked vehicle using its ID."
    )
    @GetMapping("/Fetch/{vehicleId}")
    public ResponseEntity<?> fetchVehicle(@PathVariable int vehicleId){
        return ResponseEntity.ok(   parkingService.fetchVehicleId(vehicleId));
    }

    @Operation(
            summary = "Exit vehicle",
            description = "Calculates parking fee and marks the vehicle as exited."
    )
    @PostMapping("/exit/{exitvehicleId}")

    public ResponseEntity<?> extVehicle(@PathVariable int exitvehicleId)
    {
        return ResponseEntity.ok(parkingService.exitVehicle(exitvehicleId));
    }




}
