package com.galahad.parking.entities.dto;

import com.galahad.parking.entities.Parked;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ExitParkingDto {
    @NotBlank(message = "The car plate provided is invalid")
    private String carPlate;

    public Parked toParked() {
        return new Parked()
                .setCarPlate(carPlate);
    }
}
