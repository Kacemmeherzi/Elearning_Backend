package com.Elrearning.models;

import jakarta.persistence.*;

@Entity
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_chapitre;
    @Column(name = "description_chapitre")
    private String chapter_description ;
@ManyToOne
private Course course ;

    public Chapter(String chapterDescription, Course course) {
        chapter_description = chapterDescription;
        this.course = course;
    }

    public Chapter() {

    }

    public void setId_chapitre(Long id) {
        this.id_chapitre = id;
    }

    public Long getId_chapitre() {
        return id_chapitre;
    }

    public String getChapter_description() {
        return chapter_description;
    }

    public void setChapter_description(String chapter_description) {
        this.chapter_description = chapter_description;
    }
}
