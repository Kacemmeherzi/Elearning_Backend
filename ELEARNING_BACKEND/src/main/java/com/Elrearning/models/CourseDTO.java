package com.Elrearning.models;

public class CourseDTO {


    public CourseDTO(){super();}

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }

    private   String course_description ;

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    private int idCategory ;
private String titre ;
    public CourseDTO(String courseDescription, int idCategory, String titre) {
        course_description = courseDescription;
        this.idCategory = idCategory;
        this.titre = titre;
    }
    public String getCourse_description() {
        return course_description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
