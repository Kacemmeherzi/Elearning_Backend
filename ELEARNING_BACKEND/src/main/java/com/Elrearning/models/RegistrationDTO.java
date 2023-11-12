package com.Elrearning.models;

public class RegistrationDTO {
    private String username;



    private String password;

    public String getEmail() {
        return email;
    }

    private String email;

    private String usertype ;
    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }




    public RegistrationDTO(String username, String password,String email,String usertype){
        super();
        this.username = username;
        this.password = password;
        this.email=email ;
        this.usertype = usertype ;
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

    public String toString(){
        return "Registration info: username: " + this.username + " password: " + this.password;
    }
}
