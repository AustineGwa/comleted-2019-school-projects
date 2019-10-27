package com.gwazasoftwares.myapplication.models;

import java.io.Serializable;

public class Employee implements Serializable {
   private String name;
   private String email;
   private String department;
   private String group;

    public Employee() {
    }

    public Employee(String name, String email, String department, String group) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
