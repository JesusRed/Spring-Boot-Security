package com.galahad.parking.repositories;

import com.galahad.parking.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Long> {
    Person findByPersonName(String personName);
}
