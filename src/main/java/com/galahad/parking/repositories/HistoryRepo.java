package com.galahad.parking.repositories;

import com.galahad.parking.entities.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoryRepo extends JpaRepository<History, Long> {
    List<History> findByCarPlate(String carPlate);
}
