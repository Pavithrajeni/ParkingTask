package com.example.ParkingTask.Exception;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException(String message){

        super   (message);
    }
}
