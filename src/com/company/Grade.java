package com.company;

public class Grade {
    private int id;
    private String teacher;
    private String student;
    private String group;
    private String subject;
    private int grade;

    public int getId() {return id;}
    public int getGrade() {return grade;}
    public String getTeacher() {return teacher;}
    public String getGroup() {return group;}
    public String getStudent() {return student;}
    public String getSubject() {return subject;}

    public void setId(int id) {this.id = id;}
    public void setGrade(int grade) {this.grade = grade;}
    public void setGroup(String group) {this.group = group;}
    public void setTeacher(String teacher) {this.teacher = teacher;}
    public void setStudent(String student) {this.student = student;}
    public void setSubject(String subject) {this.subject = subject;}
}
