package com.galahad.parking.services.interfaces;

import com.galahad.parking.entities.History;
import com.galahad.parking.entities.Parked;
import com.galahad.parking.entities.dto.SendEmailDto;
import java.util.List;

public interface ParkingService {
    Parked addCarToParking(Parked parked);

    List<Parked> getParkeds();

    History exitFromParking(Parked parked);

    List<History> getHistory();

    List<History> getCarHistory(String carPlate);

    String sendEmail(SendEmailDto sendEmailDto);
}
