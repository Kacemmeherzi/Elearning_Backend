package com.Elrearning.controllers;

import com.Elrearning.models.Course;
import com.Elrearning.models.CourseDTO;
import com.Elrearning.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
@Autowired
private final CourseService courseService ;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/addcourse/{id}")
    public ResponseEntity<?> addcourse (@RequestBody CourseDTO course , @PathVariable int id ) {

        courseService.addcourse(course, id );
        return  new ResponseEntity<>(course, HttpStatus.CREATED);


    }








}
