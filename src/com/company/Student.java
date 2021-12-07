package com.company;

public class Student {

    private int id;
    private String name;
    private String surname;
    private String group;
    private int grade;


    public Integer getId(){return id;}
    public String getName(){return name;}
    public String getSurname(){return surname;}
    public String getGroup(){return group;}
    public int getGrade(){return grade;}

    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setSurname(String surname) {this.surname = surname;}
    public void setGroup(String group) {this.group = group;}
    public void setGrade(int grade) {this.grade = grade;}
}
