package com.Elrearning.controllers;

import com.Elrearning.repository.UserRepository;
import com.Elrearning.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Elrearning.models.User;
import com.Elrearning.models.LoginResponseDTO;
import com.Elrearning.models.RegistrationDTO;
import com.Elrearning.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserRepository userRepository ;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody String username ,String password ,String email ){
        if (userRepository.findByUsername(username).isPresent()){
            return new ResponseEntity<>("ALREADY EXIST",HttpStatus.NOT_ACCEPTABLE);

        }
      else {
        authenticationService.registerUser(username,password,email);
        return (ResponseEntity<?>) ResponseEntity.ok("ADDED");}
    }
    
    @PostMapping("/login")
    public ResponseEntity<?>  loginUser(@RequestBody RegistrationDTO body){
LoginResponseDTO dto = authenticationService.loginUser(body.getUsername(), body.getPassword());
if (dto.getUser()!= null){
return   ResponseEntity.ok(dto);}
else return  new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);


    }
}   
