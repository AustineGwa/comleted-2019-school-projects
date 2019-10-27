package com.gwazasoftwares.gwaza.carshare.models;

public class JoinCarRequest {
  private  String email;

    public JoinCarRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
