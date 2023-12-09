package com.Elrearning.models;

public class ChapterDTO {
    private String titre ;
    private String description ;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChapterDTO(String titre, String description) {
        this.titre = titre;
       this.description = description;
    }
}
