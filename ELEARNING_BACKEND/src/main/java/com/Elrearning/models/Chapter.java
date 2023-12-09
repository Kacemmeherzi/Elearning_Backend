package com.Elrearning.models;

import jakarta.persistence.*;

@Entity
@Table(name = "chapitres")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_chapitre;
    @Column(name = "titre")
    private String titre ;
    @Column(name = "description_chapitre")
    private String chapter_description ;
    @Column(name = "vedio_name")
    private String vedio_name ;
@ManyToOne
@JoinColumn(name ="idCourse")
private Course course ;

    public Chapter(String titre, String chapterDescription,  Course course) {
        this.titre = titre;
        chapter_description = chapterDescription;
        ;
        this.course = course;
    }

    public Chapter() {

    }

    public String getVedio_name() {
        return vedio_name;
    }

    public void setVedio_name(String vedio_name) {
        this.vedio_name = vedio_name;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id_chapitre=" + id_chapitre +
                ", titre='" + titre + '\'' +
                ", chapter_description='" + chapter_description + '\'' +
                ", course=" + course +
                '}';
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
