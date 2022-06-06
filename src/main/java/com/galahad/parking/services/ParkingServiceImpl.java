package com.galahad.parking.services;

import com.galahad.parking.entities.History;
import com.galahad.parking.entities.Parked;
import com.galahad.parking.repositories.HistoryRepo;
import com.galahad.parking.repositories.ParkedRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ParkingServiceImpl implements ParkingService {

    private final ParkedRepo parkedRepo;
    private final HistoryRepo historyRepo;

    @Override
    public Parked addCarToParking(Parked parked) {
        Optional<Parked> parkedOptional = parkedRepo.findByCarPlate(parked.getCarPlate());
        if (parkedOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede Registrar Ingreso, ya existe la placa");
        } else if (parkedRepo.count() > 4) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Parqueadero lleno");
        }
        log.info("Saving car {} into the parking", parked.getCarPlate());
        parked.setEntryDate(LocalDateTime.now());
        return parkedRepo.save(parked);
//        Parked parked = new Parked();
//        parked.setEntryDate(LocalDateTime.now());
//        parked.setCarPlate(plate);
    }

    @Override
    public List<Parked> getParkeds() {
        log.info("Showing parking spaces");
        return parkedRepo.findAll();
    }

    @Override
    public Parked exitFromParking(Parked parked) {
        Optional<Parked> parkedOptional = parkedRepo.findByCarPlate(parked.getCarPlate());
        if (parkedOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede Registrar Salida, no existe la placa");
        }
        History history = new History();
        history.setCarPlate(parkedOptional.get().getCarPlate());
        history.setEntryDate(parkedOptional.get().getEntryDate());
        history.setExitDate(LocalDateTime.now());
        historyRepo.save(history);
        log.info("Deleting car {} into the parking", parked.getCarPlate());
        parkedRepo.deleteByCarPlate(parkedOptional.get().getCarPlate());
        return parkedOptional.get();
    }

    @Override
    public List<History> getHistory() {
        log.info("Showing history of parking");
        return historyRepo.findAll();
    }

    @Override
    public List<History> getCarHistory(String carPlate) {
        log.info("Showing car history of {}", carPlate);
        return historyRepo.findByCarPlate(carPlate);
    }
}
