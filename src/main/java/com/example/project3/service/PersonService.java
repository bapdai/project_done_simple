package com.example.project3.service;

import com.example.project3.entity.Person;
import com.example.project3.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    public List<Person> findAll(){
        return personRepository.findAll();
    }
    public Optional<Person> findById(Integer id){
        return personRepository.findById(id);
    }
    public Person save(Person person){
        return personRepository.save(person);
    }
    public void deleteById(Integer id){
        personRepository.deleteById(id);
    }
}
