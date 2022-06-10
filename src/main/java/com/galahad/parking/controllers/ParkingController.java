package com.galahad.parking.controllers;

import com.galahad.parking.entities.History;
import com.galahad.parking.entities.Parked;
import com.galahad.parking.entities.dto.ExitParkingDto;
import com.galahad.parking.entities.dto.SendEmailDto;
import com.galahad.parking.repositories.HistoryRepo;
import com.galahad.parking.services.ParkingServiceImpl;
import com.galahad.parking.services.interfaces.ParkingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;
    private final HistoryRepo historyRepo;
    private final ParkingServiceImpl parkingService1;

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
    public ResponseEntity<List<History>> getCarDetails(@PathVariable("carPlate") String carPlate) {
        return ResponseEntity.ok().body(parkingService.getCarHistory(carPlate));
    }

    //TAREA: REGISTRAR ENTRADA DE VEHICULOS
    //ROL: USER
    @PostMapping("/parking/entry")
    public Parked addCarToParking(@Valid @RequestBody Parked parked) {
        return parkingService.addCarToParking(parked);
    }

    //TAREA: REGISTRAR SALIDA DE VEHICULOS
    //ROL: USER
    @Transactional
    @PostMapping("/parking/exit")
    public ResponseEntity<String> exitFromParking(@Valid @RequestBody ExitParkingDto exitParkingDto) {
        parkingService.exitFromParking(exitParkingDto.toParked());
        return ResponseEntity.status(HttpStatus.CREATED).body("Salida registrada");
    }

    //TAREA: CONTAR EL VEHICULO MAS REPETIDO
    //ROL: DESCONOCIDO
    @GetMapping("/parking/common")
    public List<Map<String, Object>> countCarPlate() {
        return parkingService1.findCommonCar();
    }

    //TAREA: BUSACR ENTRADA EN EL HISTORIAL
    //ROL: DESCONOCIDO
    @GetMapping("parking/history/{beginDate}/{endDate}")
    public List<Map<String, Object>>  getCarPlateByDate(@Valid @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date beginDate, @PathVariable(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate) {
        return parkingService1.getCarPlateByDate(beginDate, endDate);
    }

    //TAREA: LISTADO DE POR PRIMERA VEZ EN PARKING
    //ROL: DESCONOCIDO
    @GetMapping("parking/firsttime")
    public List<Parked> getByFirstTime() {
        return parkingService1.getByFirstTime();
    }


    //TAREA: ENVIO DE CORREO AL PARQUEADO
    //ROL: ADMIN
    @PostMapping(path = "/parking/email", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendMailParking(@Valid @RequestBody SendEmailDto sendEmailDto) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingService.sendEmail(sendEmailDto));
       //return parkingService.sendEmail(sendEmailDto);
    }
}
