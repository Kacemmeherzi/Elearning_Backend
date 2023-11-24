package com.Elrearning.models;

import jakarta.persistence.*;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
@Column(name = "label",unique = true)
    private  String label;

}
