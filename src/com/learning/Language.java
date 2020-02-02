package com.learning;

import java.lang.String;

public class Language {
    private int ID;
    private String name;
    private String dateCreated;
    private String dateModified;

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public int getID() {
        return this.ID;
    }

    public String getDateCreated(){
        return this.dateCreated;
    }

    public String getDateModified(){
        return this.dateModified;
    }

    // Constructors
    public Language(){}

    public Language(String name){
        this.name = name;
    }
}
