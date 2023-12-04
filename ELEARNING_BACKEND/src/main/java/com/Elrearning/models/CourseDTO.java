package com.Elrearning.models;

public class CourseDTO {

    public String getCourse_description() {
        return course_description;
    }
    public CourseDTO(){super();}

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }

    private   String course_description ;

    public CourseDTO(String courseDescription) {
        course_description = courseDescription;
    }
}
