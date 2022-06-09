package com.galahad.parking.repositories;

import com.galahad.parking.entities.History;
import com.galahad.parking.entities.Parked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkedRepo extends JpaRepository<Parked, Long> {
    Optional<Parked> findByCarPlate(String carPlate);
    void deleteByCarPlate(String carPlate);




}
