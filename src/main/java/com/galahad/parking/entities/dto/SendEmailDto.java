package com.galahad.parking.entities.dto;

import com.galahad.parking.entities.Parked;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SendEmailDto {
    @NotBlank(message = "The email provided is invalid")
    @Email(message = "The email address is invalid.", flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String email;

    @NotBlank(message = "Message invalid")
    private String message;

    @NotBlank
    @Size(min = 6, max = 6, message = "The car plate provided is invalid")
    private String carPlate;

    public Parked toParked() {
        return new Parked()
                .setEmailCar(email)
                .setMessage(message)
                .setCarPlate(carPlate);
    }

}
