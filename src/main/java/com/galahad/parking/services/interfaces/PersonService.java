package com.galahad.parking.services.interfaces;

import com.galahad.parking.entities.Person;

import java.util.Optional;

public interface PersonService {
    Person savePerson(Person person);

    Optional<Person> getPerson(String personName);
    // Role saveRole(Role role);
    // void addRoleToPerson(String personName, String roleName);
    // List<Person>getPersons();
}
