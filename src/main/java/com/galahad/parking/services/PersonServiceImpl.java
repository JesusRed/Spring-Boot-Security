package com.galahad.parking.services;

import com.galahad.parking.entities.Person;
import com.galahad.parking.repositories.PersonRepo;
import com.galahad.parking.services.interfaces.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PersonServiceImpl implements PersonService, UserDetailsService {
    private final PersonRepo personRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String personname) throws UsernameNotFoundException {
        Optional<Person> person = personRepo.findByPersonName(personname);
        if (person.isEmpty()) {
            log.error("User not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        } else {
            log.info("Person {} found", personname);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(person.get().getRole()));
        return new org.springframework.security.core.userdetails.User(person.get().getPersonName(), person.get().getPassword(), authorities);
    }

    public Person savePerson(Person person) {
        Optional<Person> optionalPerson = personRepo.findByPersonName(person.getPersonName());
        if (optionalPerson.isPresent()) {
            log.info("error Saving person {} to the db", person.getPersonName());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person exists");
        }
        log.info("Saving {} in database", person.getRole());
        person.setRole("USER");
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepo.save(person);
    }

    @Override
    public Optional<Person> getPerson(String personName) {
        log.info("Showing person {} ", personName);
        return personRepo.findByPersonName(personName);
    }

//    @Override
//    public List<Person> getPersons() {
//        log.info("Showing all persons");
//        return personRepo.findAll();
//    }


}
