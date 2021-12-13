package com.company;

import com.mysql.cj.log.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin implements ActionListener {
    JFrame frame = new JFrame();

    JButton logoutButton = new JButton("LogOut");
    JLabel helloLabel = new JLabel("Hello, Admin");

    JButton editStudentButton = new JButton("Edit Student");
    JButton addStudentButton = new JButton("Add Student");
    JButton editTeacherButton = new JButton("Edit Teacher");
    JButton addTeacher = new JButton("Add Teacher");
    Admin(){
        logoutButton.setBounds(300,25,75,25);
        helloLabel.setBounds(100,25,75,25);
        editStudentButton.setBounds(30, 70, 120, 30);
        addStudentButton.setBounds(30, 110, 120, 30);
        editTeacherButton.setBounds(160, 70, 120, 30);
        addTeacher.setBounds(160,110,120,30);

        logoutButton.addActionListener(this);
        editStudentButton.addActionListener(this);
        addStudentButton.addActionListener(this);
        editTeacherButton.addActionListener(this);
        addTeacher.addActionListener(this);

        frame.add(logoutButton);
        frame.add(helloLabel);
        frame.add(editStudentButton);
        frame.add(addStudentButton);
        frame.add(editTeacherButton);
        frame.add(addTeacher);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutButton){
            Login login = new Login();
            frame.dispose();
        }
        if (e.getSource() == editStudentButton){
            EditStudent editStudent = new EditStudent();
            frame.dispose();
        }
        if (e.getSource() == addStudentButton){

            AddStudent addStudent = new AddStudent();
            frame.dispose();
        }
        if (e.getSource() == editTeacherButton){
            EditTeacher editTeacher = new EditTeacher();
            frame.dispose();
        }
        if (e.getSource() == addTeacher){
            AddTeacher addTeacher = new AddTeacher();
            frame.dispose();
        }
    }
}
