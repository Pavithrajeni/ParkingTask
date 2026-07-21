package com.example.ParkingTask.DTO;

import com.example.ParkingTask.Entity.parkingEntity;

public class ParkingDto {

    public Integer vehicleNumber;
    public String vehicleType;
    public String vehicleColor;
    public String vehicleBrand;
    private Long fee;
    private parkingEntity.VehicleStatus status;

    public Integer getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(Integer vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public parkingEntity.VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(parkingEntity.VehicleStatus status) {
        this.status = status;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }
}
