package com.example.ParkingTask.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class tariffEntity {

    @Id
    public Integer id;
    public Integer duration;
    public Integer amount;
    public tariffEntity(){}

    @Override
    public String toString() {
        return "tariffEntity{" +
                "id=" + id +
                ", duration=" + duration +
                ", amount=" + amount +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }


    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
