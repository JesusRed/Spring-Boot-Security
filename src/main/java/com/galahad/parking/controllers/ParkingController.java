package com.galahad.parking.controllers;

import com.galahad.parking.dto.CarParkingDto;
import com.galahad.parking.entities.History;
import com.galahad.parking.entities.Parked;
import com.galahad.parking.services.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    //TAREA: LISTADO DE VEHICULOS EN EL PARQUEADERO
    //ROL: ADMIN Y USER
    @GetMapping("/parking")
    public ResponseEntity<List<Parked>> getParkeds() {
        return ResponseEntity.ok().body(parkingService.getParkeds());
    }

    //TAREA: REPORTE DE HISTORIAL
    //ROL: ADMIN
    @GetMapping("/parking/history")
    public ResponseEntity<List<History>> getHistory() {
        return ResponseEntity.ok().body(parkingService.getHistory());
    }

    //TAREA: DETALLE DEL VEHICULO EN EL HISTORIAL
    //ROL: DESCONOCIDO
    @GetMapping("/parking/car/{carPlate}")
    public ResponseEntity<List<History>> getCarDetails(@PathVariable String carPlate) {
        return ResponseEntity.ok().body(parkingService.getCarHistory(carPlate));
    }

    //TAREA: RESGITRAR ENTRADA DE VEHICULOS
    //ROL: USER
    @PostMapping("/parking/entry")
    public ResponseEntity<Parked> addCarToParking(@Valid @RequestBody CarParkingDto carParkingDto) {
        Parked parked = parkingService.addCarToParking(carParkingDto.ToParked());
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/parking/entry").toUriString());
        return ResponseEntity.created(uri).body(parkingService.addCarToParking(parked));
    }

    //TAREA: REGISTRAR SALIDA DE VEHICULOS
    //ROL: USER
    @PostMapping("/parking/exit")
    public ResponseEntity<Parked> exitFromParking(@RequestBody Parked parked) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/parking/exit").toUriString());
        return ResponseEntity.status(HttpStatus.OK).body(parkingService.exitFromParking(parked));
    }
}
