package com.Elrearning.services;

import com.Elrearning.models.Chapter;
import com.Elrearning.models.ChapterDTO;
import com.Elrearning.models.Course;
import com.Elrearning.repository.ChapterRepository;
import com.Elrearning.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterService {
    @Autowired
   private final ChapterRepository chapterRepository ;
    @Autowired
     private final CourseRepository courseRepository ;

    public ChapterService(ChapterRepository chapterRepository, CourseRepository courseRepository, CourseService courseService, CourseRepository courseRepository1) {
        this.chapterRepository = chapterRepository;


        this.courseRepository = courseRepository1;
    }

    public boolean addchapter (long courseid, ChapterDTO chapterDTO){


if (courseRepository.existsById(courseid)){

    Optional<Course> course = courseRepository.findById(courseid) ;
    Chapter chapter = new Chapter(chapterDTO.getTitre(), chapterDTO.getDescription(), course.get());
    System.out.println(chapter);
    course.get().addchapter(chapter);
    chapterRepository.save(chapter);
    courseRepository.saveAndFlush(course.get());
    return true ;
}
else
        return false;
    }
}
