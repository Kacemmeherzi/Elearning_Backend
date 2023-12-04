package com.Elrearning.models;

import jakarta.persistence.*;


import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cours")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCourse;
    @Column(name = "description_cours")
    private String course_description;
    @Column(name = "date dajout ")

    private Date date_ajout ;
    @Column(name = "date modification ")
    private Date date_modif ;

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }

    @OneToMany(mappedBy = "id_chapitre")
    private List<Chapter> chapterList ;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne()
    private User user ;
    public Course() {

    }

    public String getCourse_description() {
        return course_description;
    }

    public void setCourse_description(String description) {
        this.course_description = description;
    }

    public Date getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }

    public Date getDate_modif() {
        return date_modif;
    }

    public void setDate_modif(Date date_modif) {
        this.date_modif = date_modif;
    }



    public Course(String description,  User user) {

        this.course_description = description;
       this.date_ajout = new Date();


        this.user = user;
    }


    public void setIdCourse(Long id) {
        this.idCourse = id;
    }

    public Long getIdCourse() {
        return idCourse;
    }

}
