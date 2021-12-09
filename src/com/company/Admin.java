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

    Admin(){

        logoutButton.setBounds(300,25,75,25);
        helloLabel.setBounds(100,25,75,25);
        editStudentButton.setBounds(30, 70, 120, 30);

        logoutButton.addActionListener(this);
        editStudentButton.addActionListener(this);

        frame.add(logoutButton);
        frame.add(helloLabel);
        frame.add(editStudentButton);

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
    }
}
