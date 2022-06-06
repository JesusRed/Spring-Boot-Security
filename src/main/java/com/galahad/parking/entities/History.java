package com.galahad.parking.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class History {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    @Size(min = 6, max = 6)
    private String carPlate;
    @Column(name = "entryDate", nullable = false)
    private LocalDateTime entryDate;
    @Column(name = "exitDate", nullable = false)
    private LocalDateTime exitDate;
}
