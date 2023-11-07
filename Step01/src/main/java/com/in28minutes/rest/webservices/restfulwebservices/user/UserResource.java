package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import jakarta.validation.Valid;

@RestController
public class UserResource {

    private UserDaoService service;
    public UserResource(UserDaoService service){
        this.service = service;
    }
    @GetMapping("/users")
            public List<user> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public user retrieveUser(@PathVariable int id){
        user User= service.findOne(id);
        if (User==null)
            throw new UserNotFoundException("id:"+id);
        return User;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        service.deletebyId(id);
    }

    @PostMapping("/users")
    public ResponseEntity<user> createUser(@Valid @RequestBody user User){
        user savedUser= service.save(User);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return  ResponseEntity.created(location).build();

    }
}
