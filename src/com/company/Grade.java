package com.company;

public class Grade {

    private int id;
    private String teacher;
    private String subject;
    private int grade;

    public int getGrade() {return grade;}
    public String getTeacher() {return teacher;}
    public String getSubject() {return subject;}

    public void setGrade(int grade) {this.grade = grade;}
    public void setTeacher(String teacher) {this.teacher = teacher;}
    public void setSubject(String subject) {this.subject = subject;}
}
