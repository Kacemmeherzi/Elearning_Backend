package com.Elrearning.services;

import com.Elrearning.models.Course;
import com.Elrearning.models.CourseDTO;
import com.Elrearning.models.User;
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
    private  final UserRepository userRepository ;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }
    public List<Course> getall () {

return  courseRepository.findAll();

    }
    public void addcourse (CourseDTO courseDTO, int id ) {
        Optional<User> user = userRepository.findById(id);
Course course = new Course(courseDTO.getCourse_description(),user.get());
        courseRepository.save(course) ;

    }
}
