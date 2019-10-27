package com.gwazasoftwares.myapplication.models;

public class LeaveRequestData {
    private  String userEmail;
    private  String userMessage;

    public LeaveRequestData(String userEmail, String userMessage) {
        this.userEmail = userEmail;
        this.userMessage = userMessage;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
