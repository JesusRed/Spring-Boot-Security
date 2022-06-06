package com.galahad.parking.services;

import com.galahad.parking.entities.History;
import com.galahad.parking.entities.Parked;

import java.util.List;

public interface ParkingService {
    Parked addCarToParking(Parked parked);
    List<Parked>getParkeds();
    Parked exitFromParking(Parked parked);
    List<History>getHistory();
    List<History>getCarHistory(String carPlate);
}
