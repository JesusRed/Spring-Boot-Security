package com.galahad.parking.repositories;

import com.galahad.parking.entities.History;
import com.galahad.parking.entities.Parked;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface HistoryRepo extends JpaRepository<History, Long> {
    List<History> findByCarPlate(String carPlate);

    @Query(
            value = "SELECT h.car_plate, COUNT(h.car_plate) AS total "
                    + " FROM History h "
                    + " GROUP BY h.car_plate "
                    + " ORDER BY total DESC "
                    + " FETCH FIRST 10 ROWS ONLY",
            nativeQuery = true
    )
    List<Map<String, Object>> countAllByCarPlate();

    @Query(
            value = "SELECT h.car_plate, h.entry_date"
                    + " FROM History h "
                    + " WHERE h.entry_date BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    List<History> findByBeginDateAndEndDate(LocalDateTime beginDate, LocalDateTime endDate);

    @Query(
            value = "SELECT * " +
                    "FROM parked p " +
                    "WHERE NOT EXISTS (SELECT * " +
                    "FROM history h " +
                    "WHERE p.car_plate = h.car_plate)",
            nativeQuery = true
    )
    List<Parked> getByFirstTime();
}
