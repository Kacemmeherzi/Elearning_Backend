package com.Elrearning.controllers;

import com.Elrearning.models.Category;
import com.Elrearning.models.CategotyDTO;
import com.Elrearning.models.Course;
import com.Elrearning.models.CourseDTO;
import com.Elrearning.repository.CategoryRepository;
import com.Elrearning.repository.UserRepository;
import com.Elrearning.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/course")
public class CourseController {
@Autowired
private final CourseService courseService ;
@Autowired
private final UserRepository userRepository ;
@Autowired
private  final CategoryRepository categoryRepository ;
    public CourseController(CourseService courseService, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.courseService = courseService;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/allcourses")
    public ResponseEntity<?> getallcourses () {

        return  new ResponseEntity<>(courseService.getall(),HttpStatus.OK) ;


    }
    @PostMapping("/addcourse/{id}")
    public ResponseEntity<?> addcourse (@RequestBody CourseDTO coursedto , @PathVariable int id ) {



if (userRepository.findById(id).isEmpty()){
    return  new ResponseEntity<>("CANT FIND USER ", HttpStatus.NOT_FOUND);

} else if ( categoryRepository.findById(coursedto.getIdCategory()).isEmpty()) {
    return  new ResponseEntity<>("CANT FIND  CATEGORY", HttpStatus.NOT_FOUND);

} else {
    Course course = courseService.addcourse(coursedto, id);
    return new ResponseEntity<>(course, HttpStatus.CREATED);

}
    }








}
