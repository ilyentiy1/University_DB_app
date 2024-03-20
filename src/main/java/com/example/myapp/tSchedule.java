package com.example.myapp;

public class tSchedule {
    String day;
    String grpNum;
    String type;
    String building;
    String room;
    String time;
    String subj;

    public tSchedule(String day, String time, String grpNum, String type, String subj, String room, String building) {
        this.day = day;
        this.grpNum = grpNum;
        this.type = type;
        this.building = building;
        this.room = room;
        this.time = time;
        this.subj = subj;
    }

    public tSchedule() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getGrpNum() {
        return grpNum;
    }

    public void setGrpNum(String grpNum) {
        this.grpNum = grpNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubj() {
        return subj;
    }

    public void setSubj(String subj) {
        this.subj = subj;
    }
}
