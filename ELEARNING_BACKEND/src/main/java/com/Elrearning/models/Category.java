package com.Elrearning.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {
    public Category( String label, String description) {

        this.label = label;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCategory;
@Column(name = "label",unique = true)
    private  String label;
@Column(name ="description")
    private  String description ;

@OneToMany
private List<Course> courses ;


    public Category() {

    }

    public int getId() {
        return idCategory;
    }

    public void setId(int id) {
        this.idCategory = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
