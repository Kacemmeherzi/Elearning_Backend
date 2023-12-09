package com.Elrearning.controllers;

import com.Elrearning.LocalStorageConfig.FileStorageService;
import com.Elrearning.LocalStorageConfig.StorageConfig;
import com.Elrearning.models.ChapterDTO;
import com.Elrearning.repository.CourseRepository;
import com.Elrearning.services.ChapterService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;


@RestController
@CrossOrigin("*")
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private final ChapterService chapterService;
@Autowired
private final  CourseRepository courseRepository ;
@Autowired
private final FileStorageService fileStorageService ;
    @Autowired
    private  final StorageConfig fileStorageConfig;
    public ChapterController(ChapterService chapterService, CourseRepository courseRepository, FileStorageService fileStorageService, StorageConfig fileStorageConfig) {
        this.chapterService = chapterService;
        this.courseRepository = courseRepository;
        this.fileStorageService = fileStorageService;
        this.fileStorageConfig = fileStorageConfig;
    }
    @PostMapping(value = "/addchapter/{id}",consumes = "multipart/form-data" )

    public ResponseEntity<?> addchapter (@PathVariable long id , @ModelAttribute ChapterDTO chapterDTO, @RequestParam MultipartFile vedio ) throws IOException {


      String vidname =  fileStorageService.saveVid(vedio);
         boolean res = chapterService.addchapter(id,chapterDTO,vidname) ;
        if (res) {return  new ResponseEntity<>("ADDED", HttpStatus.CREATED);}
        else {return  new ResponseEntity<>("CANT FIND THE COURSE",HttpStatus.NOT_FOUND);}

    }

// hethi meghir ma thot token fel header deja permitall() mahtouta
    @GetMapping("/stream/{videoFileName}")
    public ResponseEntity<StreamingResponseBody> streamVideo(@PathVariable String videoFileName, HttpServletResponse response) {
        Resource videoResource = fileStorageService.loadVideoAsResource(videoFileName);

        try {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + videoFileName);

            StreamingResponseBody responseBody = outputStream -> {
                try {
                    videoResource.getInputStream().transferTo(outputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };

            return ResponseEntity.ok().body(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getchapters/{id}")
    public ResponseEntity<?> getchapters (@PathVariable long id ){
        if (courseRepository.existsById(id)){

        return  new ResponseEntity<>(chapterService.getchaptersbycourseid(id),HttpStatus.OK);
    }else  {return  new ResponseEntity<>("CANNOT FIND COURSE" ,HttpStatus.NOT_FOUND);}
}}
