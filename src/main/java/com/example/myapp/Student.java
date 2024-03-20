package com.example.myapp;

public class Student {
    String studentpk;
    String sgroupfk;
    String fio;
    String email;
    String login;
    String password;
    String groupNumber;

    /*public Student(String studentpk, String groupNumber, String fio, String email, String login, String password) {
        this.studentpk = studentpk;
        this.fio = fio;
        this.email = email;
        this.login = login;
        this.password = password;
        this.groupNumber = groupNumber;
    }*/

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getStudentpk() {
        return studentpk;
    }

    public void setStudentpk(String studentpk) {
        this.studentpk = studentpk;
    }

    public String getSgroupfk() {
        return sgroupfk;
    }

    public void setSgroupfk(String sgroupfk) {
        this.sgroupfk = sgroupfk;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Student(String studentpk, String sgroupfk, String fio, String email, String login, String password) {
        this.studentpk = studentpk;
        this.sgroupfk = sgroupfk;
        this.fio = fio;
        this.email = email;
        this.login = login;
        this.password = password;
    }
    public Student(String studentpk, String groupNumber, String fio, String email, String login, String password, int key) {
        this.studentpk = studentpk;
        this.groupNumber = groupNumber;
        this.fio = fio;
        this.email = email;
        this.login = login;
        this.password = password;
    }
    public Student() {
    }
}
