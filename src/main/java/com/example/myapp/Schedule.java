package com.example.myapp;

public class Schedule {
    String lessonpk;
    String sgroupfk;
    String roomfk;
    String teacherfk;
    String subjectfk;
    String subject;
    String room;
    String teacher;
    String sgroup;
    String type;
    String week;
    String time;
    String day;
    String department;

    public String getLessonpk() {
        return lessonpk;
    }

    public void setLessonpk(String lessonpk) {
        this.lessonpk = lessonpk;
    }

    public String getSgroupfk() {
        return sgroupfk;
    }

    public void setSgroupfk(String sgroupfk) {
        this.sgroupfk = sgroupfk;
    }

    public String getRoomfk() {
        return roomfk;
    }

    public void setRoomfk(String roomfk) {
        this.roomfk = roomfk;
    }

    public String getTeacherfk() {
        return teacherfk;
    }

    public void setTeacherfk(String teacherfk) {
        this.teacherfk = teacherfk;
    }

    public String getSubjectfk() {
        return subjectfk;
    }

    public void setSubjectfk(String subjectfk) {
        this.subjectfk = subjectfk;
    }

    public Schedule(String lessonpk, String sgroupfk, String roomfk, String teacherfk, String subjectfk,
                    String subject, String room, String teacher, String sgroup,
                    String type, String week, String time, String day, String building) {
        this.subjectfk = subjectfk;
        this.lessonpk = lessonpk;
        this.sgroupfk = sgroupfk;
        this.roomfk = roomfk;
        this.teacherfk = teacherfk;
        this.subject = subject;
        this.room = room;
        this.teacher = teacher;
        this.sgroup = sgroup;
        this.type = type;
        this.week = week;
        this.time = time;
        this.day = day;
        this.building = building;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    String building;

    public Schedule() {}

    public Schedule(String subject, String room, String teacher, String sgroup,
                    String type, String week, String time, String day, String department, String building) {
        this.subject = subject;
        this.room = room;
        this.teacher = teacher;
        this.sgroup = sgroup;
        this.type = type;
        this.week = week;
        this.time = time;
        this.day = day;
        this.department = department;
        this.building = building;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getSgroup() {
        return sgroup;
    }

    public void setSgroup(String sgroup) {
        this.sgroup = sgroup;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
