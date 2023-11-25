package com.Elrearning.models;

import jakarta.persistence.*;

@Entity
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    @Column(name = "filetype")
private String filetype ;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
   private  String name ;

    @OneToOne(mappedBy = "fileEntity", cascade = CascadeType.ALL)
    private Category category;
    public FileEntity() {

    }



    public FileEntity(String filetype,  String name) {
        this.filetype = filetype;

        this.name = name;
    }



    public void setId(int id) {
        this.id = id;
    }

    public int  getId() {
        return id;
    }

}
