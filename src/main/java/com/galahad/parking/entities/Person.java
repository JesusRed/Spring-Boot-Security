package com.galahad.parking.entities;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
@Accessors(chain = true)
@Table(name = "persons")
public class Person {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;
    @Column(nullable = false)
    private String personName;
    @Column(nullable = false)
    private String password;
    private String role;

//    public Person(String personName, String password, String role) {
//        this.personName = personName;
//        this.password = password;
//        this.role = role;
//    }
}
