package com.Elrearning.controllers;

import com.Elrearning.models.ChapterDTO;
import com.Elrearning.services.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }
    @PostMapping("/addchapter/{id}")
    public ResponseEntity<?> addchapter (@PathVariable long id , ChapterDTO chapterDTO) {
         boolean res = chapterService.addchapter(id,chapterDTO) ;
        if (res) {return  new ResponseEntity<>("ADDED", HttpStatus.CREATED);}
        else {return  new ResponseEntity<>("CANT FIND THE COURSE",HttpStatus.NOT_FOUND);}

    }
}
