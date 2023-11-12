package com.Elrearning.controllers;

import com.Elrearning.models.User;
import com.Elrearning.repository.UserRepository;
import com.Elrearning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
private final UserRepository userRepository ;
    @Autowired
    private final UserService userService ;

    public AdminController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }



@GetMapping("/getallusers")
    List<User>  getallusers () {

        return userService.getallusers();

}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        if (userService.exist(id)) {
            userService.deleteuser(id);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }







}


