package com.Elrearning.controllers;

import com.Elrearning.models.Category;
import com.Elrearning.models.CategotyDTO;
import com.Elrearning.models.RegistrationDTO;
import com.Elrearning.models.User;
import com.Elrearning.repository.UserRepository;
import com.Elrearning.services.CategorieService;
import com.Elrearning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private final CategorieService categorieService ;

    public AdminController(UserRepository userRepository, UserService userService, CategorieService categorieService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.categorieService = categorieService;
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

    @PostMapping("/addteacher")
    public ResponseEntity<?> addTeacher (@RequestBody RegistrationDTO user) {
        if (!userService.existbyusername(user.getUsername())){
            userService.addteacher(user) ;
            return new  ResponseEntity<>("ADDED",HttpStatus.CREATED);
        } else {

            return  new ResponseEntity<>("ALREADY EXIST",HttpStatus.EXPECTATION_FAILED);
        }
    }

@GetMapping("/getallteachers")
    public List<User> getAllTeachers () {
        return  userService.getallteachers();
}

@PostMapping("updateteacher/{id}")
    public ResponseEntity<String> updateTeacher (@RequestBody User user ,@PathVariable int id) {


        return new ResponseEntity<>(userService.updateuser(user,id),HttpStatus.OK);
}
@PostMapping ("/addcategory")
    public ResponseEntity<?> addcategory (@RequestBody CategotyDTO categotyDTO){
    Category category = new Category(0, categotyDTO.getLabel(), categotyDTO.getDescription() );
if (!categorieService.checkifexist(category)){

    categorieService.addcategory(category) ;
    return new ResponseEntity<>("ADDED",HttpStatus.CREATED);
}else
    return new ResponseEntity<>("ALREADY EXIST",HttpStatus.NOT_ACCEPTABLE);
}

@DeleteMapping("/deletecategory/{id}")
    public ResponseEntity<?> deletecategory(@PathVariable int id ) {
    if (categorieService.checkbyid(id)){
        categorieService.deletecategory(id);
        return  new ResponseEntity<>("DELETED", HttpStatus.OK);
    }else return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);

}
}


