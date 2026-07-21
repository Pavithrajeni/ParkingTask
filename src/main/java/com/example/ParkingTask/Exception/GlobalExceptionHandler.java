package com.example.ParkingTask.Exception;

import com.example.ParkingTask.Template.errorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateVehicleException.class)

    public ResponseEntity<errorMessages> handleDuplicateVehicle(DuplicateVehicleException ex){

        errorMessages error=new errorMessages(ex.getMessage(),
               HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(VehicleNotFoundException.class)

    public ResponseEntity<errorMessages> VehicleNotFound(VehicleNotFoundException ex){

        errorMessages errorMessages=new errorMessages(ex.getMessage(),
                HttpStatus.NOT_FOUND.value() , LocalDateTime.now());

        return  new ResponseEntity<>(errorMessages,HttpStatus.NOT_FOUND);
    }



}
