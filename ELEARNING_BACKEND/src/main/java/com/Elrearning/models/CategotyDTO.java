package com.Elrearning.models;

import org.springframework.web.multipart.MultipartFile;

public class CategotyDTO {

    private  String label ;

    public CategotyDTO(String label, String description, MultipartFile file) {
        this.label = label;
        this.description = description;
    }

    private String description ;






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
