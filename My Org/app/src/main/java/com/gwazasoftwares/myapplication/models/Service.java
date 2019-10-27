package com.gwazasoftwares.myapplication.models;

public class Service {
    private int holderImage;
    private String serverImage;
    private String name;

    public Service() {
    }

    public Service(int holderImage, String name) {
        this.holderImage = holderImage;
        this.name = name;
    }

    public Service(String serverImage, String name) {
        this.serverImage = serverImage;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHolderImage() {
        return holderImage;
    }

    public void setHolderImage(int holderImage) {
        this.holderImage = holderImage;
    }

    public String getServerImage() {
        return serverImage;
    }

    public void setServerImage(String serverImage) {
        this.serverImage = serverImage;
    }
}
