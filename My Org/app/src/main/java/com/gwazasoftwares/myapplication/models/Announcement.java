package com.gwazasoftwares.myapplication.models;

public class Announcement {
    String message;

    public Announcement() {
    }

    public Announcement(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
