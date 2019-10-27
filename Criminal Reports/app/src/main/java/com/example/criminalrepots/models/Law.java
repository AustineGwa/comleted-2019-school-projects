package com.example.criminalrepots.models;

public class Law {

    private String crimeType,punishment;

    public Law() {
    }

    public Law(String crimeType, String punishment) {
        this.crimeType = crimeType;
        this.punishment = punishment;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
    }

    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }
}
