package com.galahad.parking.services;

import com.galahad.parking.entities.Person;
import com.galahad.parking.entities.Role;
import com.galahad.parking.repositories.PersonRepo;
import com.galahad.parking.repositories.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PersonServiceImpl implements PersonService, UserDetailsService {

    private final PersonRepo personRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String personname) throws UsernameNotFoundException {
        Person person = personRepo.findByPersonName(personname);
        if (person == null) {
            log.error("User not found");
            throw new UsernameNotFoundException("Person not found in the database");
        } else {
            log.info("Person {} found", personname);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //person.getRole().forEach(role -> {
        authorities.add(new SimpleGrantedAuthority(person.getRole().getRoleName()));
        return new org.springframework.security.core.userdetails.User(person.getPersonName(), person.getPassword(), authorities);
    }

    @Override
    public Person savePerson(Person person) {
        log.info("Saving person {} to the db", person.getPersonName());
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepo.save(person);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving role {} to the database", role.getRoleName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToPerson(String personName, String roleName) {
        log.info("Adding role {} to person {}", roleName, personName);
        Person person = personRepo.findByPersonName(personName);
        Role role = roleRepo.findByRoleName(roleName);
        person.setRole(role);
    }

    @Override
    public Person getPerson(String personName) {
        log.info("Showing person {} ", personName);
        return personRepo.findByPersonName(personName);
    }

    @Override
    public List<Person> getPersons() {
        log.info("Showing all persons");
        return personRepo.findAll();
    }


}
