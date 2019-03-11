package com.example.firebase;

public class Details {
    private String name;
    private String description;
    private String email;

    public Details(){

    }

    public Details(String name,String description,String email){
        this.name = name;
        this.description = description;
        this.email = email;
    }

    public String getName(){
       return name;
    }

    public String getDescription(){
        return description;
    }

    public String getEmail(){
        return email;
    }

    public  void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setEmail(String email){
        this.email = email;
    }

}
