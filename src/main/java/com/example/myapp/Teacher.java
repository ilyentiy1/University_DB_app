package com.example.myapp;

public class Teacher {
    String teacherpk;
    String department;
    String FIO;
    String post;
    String degree;
    String email;
    String type;
    String subject;
    String password;
    String login;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Teacher(String department, String FIO, String post, String degree, String email, String login, String password) {
        this.department = department;
        this.FIO = FIO;
        this.post = post;
        this.degree = degree;
        this.email = email;
        this.password = password;
        this.login = login;
    }

    public String getTeacherpk() {
        return teacherpk;
    }

    public void setTeacherpk(String teacherpk) {
        this.teacherpk = teacherpk;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Teacher(String FIO, String subject, String type,  String email) {
        this.FIO = FIO;
        this.subject = subject;
        this.type = type;
        this.email = email;
    }

    public Teacher(String teacherpk, String FIO, String subject, String type,  String email) {
        this.FIO = FIO;
        this.subject = subject;
        this.type = type;
        this.email = email;
        this.teacherpk = teacherpk;
    }

    public Teacher() { }
}
