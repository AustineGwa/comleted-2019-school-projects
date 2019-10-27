package com.example.criminalrepots.models;

import java.io.Serializable;

public class Lawyer implements Serializable {

    private String name;
    private int yearsOfExperience;
    private String licenced;

    public Lawyer(String name, int yearsOfExperience, String licenced) {
        this.name = name;
        this.yearsOfExperience = yearsOfExperience;
        this.licenced = licenced;
    }

    public Lawyer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getLicenced() {
        return licenced;
    }

    public void setLicenced(String licenced) {
        this.licenced = licenced;
    }
}
