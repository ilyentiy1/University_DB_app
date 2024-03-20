package com.example.myapp;

public class Grade {
    String subject;
    int firstatt;
    int secondatt;
    int thirdatt;
    int test;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getFirstatt() {
        return firstatt;
    }

    public void setFirstatt(int firstatt) {
        this.firstatt = firstatt;
    }

    public int getSecondatt() {
        return secondatt;
    }

    public void setSecondatt(int secondatt) {
        this.secondatt = secondatt;
    }

    public int getThirdatt() {
        return thirdatt;
    }

    public void setThirdatt(int thirdatt) {
        this.thirdatt = thirdatt;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public int getExam() {
        return exam;
    }

    public void setExam(int exam) {
        this.exam = exam;
    }

    public int getEndSum() {
        return endSum;
    }

    public void setEndSum(int endSum) {
        this.endSum = endSum;
    }

    int exam;
    int endSum;

    public Grade(String subject, int firstatt, int secondatt, int thirdatt, int test, int exam, int endSum) {
        this.subject = subject;
        this.firstatt = firstatt;
        this.secondatt = secondatt;
        this.thirdatt = thirdatt;
        this.test = test;
        this.exam = exam;
        this.endSum = endSum;
    }
    public Grade(){}

}
