package com.Elrearning.services;

import com.Elrearning.models.Chapter;
import com.Elrearning.models.ChapterDTO;
import com.Elrearning.models.Course;
import com.Elrearning.repository.ChapterRepository;
import com.Elrearning.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {
    @Autowired
   private final ChapterRepository chapterRepository ;
    @Autowired
     private final CourseRepository courseRepository ;

    public ChapterService(ChapterRepository chapterRepository,  CourseService courseService, CourseRepository courseRepository) {
        this.chapterRepository = chapterRepository;


        this.courseRepository = courseRepository;
    }

    public boolean addchapter (long courseid, ChapterDTO chapterDTO, String vedioname) {


if (courseRepository.existsById(courseid)){

    Course course = courseRepository.findById(courseid).get() ;
    Chapter chapter = new Chapter(chapterDTO.getTitre(), chapterDTO.getDescription() , course);
    chapter.setVedio_name(vedioname);
    System.out.println(chapter.toString());
    course.addchapter(chapter);
    chapterRepository.save(chapter);
    courseRepository.saveAndFlush(course);
    return true ;
}
else
        return false;
    }
    public List<Chapter> getchaptersbycourseid(long id ){

 return  chapterRepository.findbyallbycourseid(id) ;
    }
}
