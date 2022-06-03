package com.galahad.parking.services;

import com.galahad.parking.entities.Person;
import com.galahad.parking.entities.Role;

import java.util.List;

public interface PersonService {
    Person savePerson(Person person);
    Role saveRole(Role role);
    void addRoleToPerson(String personName, String roleName);
    Person getPerson(String personName);
    List<Person>getPersons();
}
