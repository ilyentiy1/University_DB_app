package com.example.myapp;

public class tGrade {
    String pk;
    String stud;
    String subject;
    String f_att;
    String s_att;
    String t_att;
    String test;
    String exam;



    public tGrade(String pk, String f_att, String s_att, String t_att, String test, String exam) {
        this.pk = pk;
        this.f_att = f_att;
        this.s_att = s_att;
        this.t_att = t_att;
        this.test = test;
        this.exam = exam;
    }

    public tGrade() {
    }
    public String getPk() { return pk; }

    public void setPk(String pk) { this.pk = pk; }
    public String getStud() {
        return stud;
    }

    public void setStud(String stud) {
        this.stud = stud;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getF_att() {
        return f_att;
    }

    public void setF_att(String f_att) {
        this.f_att = f_att;
    }

    public String getS_att() {
        return s_att;
    }

    public void setS_att(String s_att) {
        this.s_att = s_att;
    }

    public String getT_att() {
        return t_att;
    }

    public void setT_att(String t_att) {
        this.t_att = t_att;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }
}
