package com.Elrearning.models;

import jakarta.persistence.*;

@Entity
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_chapitre;
    @Column(name = "titre")
    private String titre ;
    @Column(name = "description_chapitre")
    private String chapter_description ;

@ManyToOne
private Course course ;

    public Chapter(String titre, String chapterDescription, Course course) {
        this.titre = titre;
        chapter_description = chapterDescription;
        this.course = course;
    }

    public Chapter() {

    }


    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getChapter_description() {
        return chapter_description;
    }

    public void setChapter_description(String chapter_description) {
        this.chapter_description = chapter_description;
    }

}
