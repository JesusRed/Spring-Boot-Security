package com.galahad.parking;

import com.galahad.parking.entities.Parked;
import com.galahad.parking.entities.Person;
import com.galahad.parking.entities.Role;
import com.galahad.parking.services.ParkingService;
import com.galahad.parking.services.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class ParkingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkingApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(PersonService personService, ParkingService parkingService) {
        return args -> {
            personService.saveRole(new Role(null, "ROLE_USER"));
            personService.saveRole(new Role(null, "ROLE_ADMIN"));

            personService.savePerson(new Person(null, "red", "1234", "red@mail.com",null));
            personService.savePerson(new Person(null, "blue", "1234", "blue@mail.com", null));
            personService.savePerson(new Person(null, "yellow", "1234", "yellow@mail.com", null));
            personService.savePerson(new Person(null, "green", "1234", "green@mail.com", null));

            personService.addRoleToPerson("red", "ROLE_ADMIN");
            personService.addRoleToPerson("blue", "ROLE_USER");
            personService.addRoleToPerson("yellow", "ROLE_USER");
            personService.addRoleToPerson("green", "ROLE_USER");

            parkingService.addCarToParking(new Parked("123ABC"));
            parkingService.addCarToParking(new Parked("456CBA"));
            parkingService.addCarToParking(new Parked("789CAB"));

        };
    }

}
