package com.example.tasksmanager.tasksmanager.controller;

import com.example.tasksmanager.tasksmanager.dao.UserRepository;
import com.example.tasksmanager.tasksmanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepo;


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        try {
            List<User> userList = userRepo.findAll();

            if (userList.isEmpty()){
                return new ResponseEntity<>(userList, HttpStatus.NO_CONTENT);
            }
             return new ResponseEntity<>(userList, HttpStatus.OK);

        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId){
        Optional<User> userData = userRepo.findById(userId);

        return userData.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user){
        User userObj = userRepo.save(user);

        return new ResponseEntity<>(userObj, HttpStatus.OK);

    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User newUserData){
        Optional<User> oldUserData = userRepo.findById(userId);

        if (oldUserData.isPresent()){
            User updatedUserData = oldUserData.get();
            updatedUserData.setUserName(newUserData.getUserName());
            updatedUserData.setFirstName(newUserData.getFirstName());
            updatedUserData.setLastName(newUserData.getLastName());

            User newUser = userRepo.save(updatedUserData);
            return new ResponseEntity<>(newUser, HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
