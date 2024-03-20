package com.example.myapp;

public class sgroup {
     String FIO;
     String email;

     private int groupNumber;
     private String grouppk;

    public sgroup(int groupNumber, String grouppk) {
        this.groupNumber = groupNumber;
        this.grouppk = grouppk;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getGrouppk() {
        return grouppk;
    }

    public void setGrouppk(String grouppk) {
        this.grouppk = grouppk;
    }

    public sgroup(String FIO, String email) {
        this.FIO = FIO;
        this.email = email;
    }

    public sgroup() {
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
