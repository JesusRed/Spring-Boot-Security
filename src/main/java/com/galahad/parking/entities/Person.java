package com.galahad.parking.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class Person {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @ToString.Include
    @Column(name = "name", nullable = false)
    private String personName;
    @ToString.Include
    @Column(name = "password", nullable = false)
    private String password;
    @ToString.Include
    @Column(name = "email", nullable = false)
    private String email;
    @ToString.Include
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Role role;

}
