package com.example.myapp;

public class Department {
    private String deppk;
    private String facultyfk;
    private String name;

    public Department(String deppk, String facultyfk, String name) {
        this.deppk = deppk;
        this.facultyfk = facultyfk;
        this.name = name;
    }

    public Department() {
    }

    public String getDeppk() {
        return deppk;
    }

    public void setDeppk(String deppk) {
        this.deppk = deppk;
    }

    public String getFacultyfk() {
        return facultyfk;
    }

    public void setFacultyfk(String facultyfk) {
        this.facultyfk = facultyfk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
