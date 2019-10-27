package com.example.smartambulance.models;

import android.location.Location;

public class AccidentReport {
    private AccidentLocation accidentLocation;
    private  String reportingUser;

    public AccidentReport() {
    }

    public AccidentReport(AccidentLocation accidentLocation, String reportingUser) {
        this.accidentLocation = accidentLocation;
        this.reportingUser = reportingUser;
    }

    public AccidentLocation getAccidentLocation() {
        return accidentLocation;
    }

    public void setAccidentLocation(AccidentLocation accidentLocation) {
        this.accidentLocation = accidentLocation;
    }

    public String getReportingUser() {
        return reportingUser;
    }

    public void setReportingUser(String reportingUser) {
        this.reportingUser = reportingUser;
    }
}
