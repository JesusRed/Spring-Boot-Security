package com.galahad.parking.entities;



import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class Role {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @ToString.Include
    @Column(nullable = false)
    private String roleName;
}
