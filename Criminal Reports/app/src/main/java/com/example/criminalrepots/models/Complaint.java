package com.example.criminalrepots.models;

public class Complaint {

    private String userEmail;
    private String userMessage;
    private String complaintDate;
    private String reportType;
    private String messaageStatus;

    public Complaint() {
    }

    public Complaint(String userEmail, String userMessage, String complaintDate, String reportType, String messaageStatus) {
        this.userEmail = userEmail;
        this.userMessage = userMessage;
        this.complaintDate = complaintDate;
        this.reportType = reportType;
        this.messaageStatus = messaageStatus;
    }

    public String getMessaageStatus() {
        return messaageStatus;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public void setMessaageStatus(String messaageStatus) {
        this.messaageStatus = messaageStatus;
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

    public String getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(String complaintDate) {
        this.complaintDate = complaintDate;
    }
}
