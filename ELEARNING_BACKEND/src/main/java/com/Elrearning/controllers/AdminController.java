package com.Elrearning.controllers;

import com.Elrearning.models.User;
import com.Elrearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
private final UserRepository userRepository ;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



@GetMapping("/getallusers")
    List<User>  getallusers () {

        return userRepository.findAll();

}








}


