package com.galahad.parking.dto;

import com.galahad.parking.entities.History;
import com.galahad.parking.entities.Parked;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class CarParkingDto {
    @NotEmpty(message = "The car plate is required")
    @Size(max = 6, min = 6, message = "Invalid car plate")
    private String carPlate;


    public Parked ToParked() {
        return new Parked().setCarPlate(carPlate).setEntryDate(LocalDateTime.now());
    }
}
