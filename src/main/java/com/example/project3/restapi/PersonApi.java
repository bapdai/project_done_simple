package com.example.project3.restapi;

import com.example.project3.entity.Person;
import com.example.project3.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project/api/person")
public class PersonApi {
    // CURD
    @Autowired
    PersonService personService;
    //
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Person>> getList(){
        return ResponseEntity.ok(personService.findAll());
    }
    //
    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer id) {
        Optional<Person> optionalPerson = personService.findById(id);
        if (!optionalPerson.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalPerson.get());
    }
    //
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return ResponseEntity.ok(personService.save(person));
    }

    //    Sua thong tin(U)
    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Person> update(@PathVariable Integer id, @RequestBody Person person) {
        Optional<Person> optionalPerson = personService.findById(id);
        if (!optionalPerson.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Person exitsPerson = optionalPerson.get();
        //     map object
        exitsPerson.setId(person.getId());
        exitsPerson.setUsername(person.getUsername());
        exitsPerson.setPassword(person.getPassword());
        exitsPerson.setAccuracy(person.getAccuracy());
        return ResponseEntity.ok(personService.save(exitsPerson));
    }
    //        Xoa thong tin
    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (!personService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        personService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
