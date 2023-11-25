package com.Elrearning.controllers;

import com.Elrearning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private  final UserService userService ;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/updatepassword/{id}")
   public ResponseEntity <?> updatepassword (@PathVariable int id ,@RequestBody String password ){

if (userService.updatepassword(password , id ) ){
    return new ResponseEntity<>("DONE", HttpStatus.OK );
}
    else return new ResponseEntity<>("SAME PASSWORD YEE BRO ", HttpStatus.NOT_ACCEPTABLE);


    }}

