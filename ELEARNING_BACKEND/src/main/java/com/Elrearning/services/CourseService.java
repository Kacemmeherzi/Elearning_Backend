package com.Elrearning.services;

import com.Elrearning.models.Category;
import com.Elrearning.models.Course;
import com.Elrearning.models.CourseDTO;
import com.Elrearning.models.User;
import com.Elrearning.repository.CategoryRepository;
import com.Elrearning.repository.ChapterRepository;
import com.Elrearning.repository.CourseRepository;
import com.Elrearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {


    @Autowired
    private  final CourseRepository courseRepository ;
    @Autowired
    private  final UserRepository userRepository ;
    @Autowired
private final CategoryRepository categoryRepository ;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository, ChapterRepository chapterRepository, CategoryRepository categoryRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;

        this.categoryRepository = categoryRepository;
    }
    public List<Course> getall () {

return  courseRepository.findAll();

    }

    public Course addcourse (CourseDTO courseDTO, int id ) {

        User user = userRepository.findById(id).get();
        Category category = categoryRepository.findById(courseDTO.getIdCategory()).get();
Course course = new Course(courseDTO.getTitre(), courseDTO.getCourse_description(),user);
course.setCategory(category);
        courseRepository.save(course) ;


return course ;
    }
}
