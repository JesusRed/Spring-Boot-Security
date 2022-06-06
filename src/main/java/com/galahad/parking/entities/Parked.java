package com.galahad.parking.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
@Accessors(chain = true)
public class Parked {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    @Size(min = 6, max = 6, message = "Invalid car plate")
    private String carPlate;
    @Column(name = "entryDate", nullable = false)
    private LocalDateTime entryDate;

    public Parked(String carPlate) {
        this.carPlate = carPlate;
    }

    public Parked(String carPlate, LocalDateTime entryDate) {
        this.carPlate = carPlate;
        this.entryDate = entryDate;
    }
}
