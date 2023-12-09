package com.Elrearning.models;

public class RegistrationDTO {
    private String username;
    private String password;



    private String email;

    public String getEmail() {
        return email;
    }


    public RegistrationDTO() {super();
    }

    public RegistrationDTO(String username, String password, String email){

        this.username = username;
        this.password = password;
        this.email=email ;

    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

}
