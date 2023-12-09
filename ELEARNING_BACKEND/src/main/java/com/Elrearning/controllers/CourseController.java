package com.Elrearning.controllers;

import com.Elrearning.models.Course;
import com.Elrearning.models.CourseDTO;
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

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping("/allcourses")
    public ResponseEntity<?> getallcourses () {

        return  new ResponseEntity<>(courseService.getall(),HttpStatus.OK) ;

    }
    @PostMapping("/addcourse/{id}")
    public ResponseEntity<?> addcourse (@RequestBody CourseDTO coursedto , @PathVariable int id ) {

      Course course= courseService.addcourse(coursedto, id );
        return  new ResponseEntity<>(course, HttpStatus.CREATED);


    }








}
