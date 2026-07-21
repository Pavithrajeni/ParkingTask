package com.example.ParkingTask.Template;


import java.time.LocalDateTime;

public class errorMessages {

    private String message;
    private int status;
    private LocalDateTime lct;

    public errorMessages(String message, int status, LocalDateTime lct) {
        this.message = message;
        this.status = status;
        this.lct = lct;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getLct() {
        return lct;
    }
}
