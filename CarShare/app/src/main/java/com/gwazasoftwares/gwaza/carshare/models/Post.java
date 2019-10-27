package com.gwazasoftwares.gwaza.carshare.models;

import java.io.Serializable;

public class Post implements Serializable {
    private int image;
    private String driverName;
    private String carOwner;
    private String size;
    private String dateOfTravel;
    private String destination;
    private String numberPlate;


    public Post() {
    }

    public Post(int image, String driverName, String carOwner, String size, String dateOfTravel, String destination, String numberPlate) {
        this.image = image;
        this.driverName = driverName;
        this.carOwner = carOwner;
        this.size = size;
        this.dateOfTravel = dateOfTravel;
        this.destination = destination;
        this.numberPlate = numberPlate;
    }

    public Post(int image, String destination) {
        this.image = image;
        this.destination = destination;
    }

    public Post(String driverName, String carOwner, String size, String dateOfTravel, String destination, String numberPlate) {
        this.driverName = driverName;
        this.carOwner = carOwner;
        this.size = size;
        this.dateOfTravel = dateOfTravel;
        this.destination = destination;
        this.numberPlate = numberPlate;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(String carOwner) {
        this.carOwner = carOwner;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDateOfTravel() {
        return dateOfTravel;
    }

    public void setDateOfTravel(String dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }
}
