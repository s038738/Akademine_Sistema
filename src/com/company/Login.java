package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {

    JFrame frame = new JFrame();
    String name = "Student";
    String[] userString = {"Admin", "Teacher", "Student"};
    JComboBox userList = new JComboBox(userString);
    JTextField userIDField = new JTextField("");
    JPasswordField userPasswordField = new JPasswordField("");
    JLabel userIDLabel = new JLabel("Username: ");
    JLabel userPasswordLabel = new JLabel("Password: ");
    JLabel messageLabel = new JLabel("");
    JLabel loginLabel = new JLabel("LOGIN");
    JButton loginButton = new JButton("Login");

    Login(){
        userIDLabel.setBounds(50,100,75,25);
        userPasswordLabel.setBounds(50,150,75,25);
        messageLabel.setBounds(125,250,175,25);
        loginLabel.setBounds(180,50,100,30);
        userIDField.setBounds(125,100,200,25);
        userPasswordField.setBounds(125,150,200,25);

        userList.setSelectedIndex(2);
        userList.setBounds(50,180,75,25);
        userList.addActionListener(this);
        loginButton.setBounds(125,210,100,25);
        loginButton.addActionListener(this);

        frame.add(loginButton);
        frame.add(userList);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userIDLabel);
        frame.add(userPasswordField);
        frame.add(userPasswordLabel);
        frame.add(loginLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == userList){
            JComboBox cb = (JComboBox)e.getSource();
            name = (String)cb.getSelectedItem();
            messageLabel.setText("");
        }
        if (e.getSource() == loginButton){
            if(name.equals("Student")){
                String name1 = userIDField.getText();
                String pass1 = String.valueOf(userPasswordField.getPassword());
                if(name1.isEmpty() || pass1.isEmpty()){
                    messageLabel.setText("Wrong input");
                }else {
                DataBase dataBase = new DataBase();
                int ats = dataBase.studentLogin(name1, pass1);
                    if(ats == 1){
                        GradeBook gradeBook = new GradeBook(name1);
                        frame.dispose();
                    }else if(ats == 0){
                        messageLabel.setText("Wrong input");
                    }
                }
            }
            if(name.equals("Teacher")) {
                String name1 = userIDField.getText();
                String pass1 = String.valueOf(userPasswordField.getPassword());
                if (name1.isEmpty() || pass1.isEmpty()) {
                    messageLabel.setText("Wrong input");
                }else{
                    DataBase dataBase = new DataBase();
                    int ats = dataBase.teacherLogin(name1, pass1);
                    if(ats == 1){
                        Teacher teacher = new Teacher(name1, pass1);
                        frame.dispose();
                    }else if(ats == 0){
                        messageLabel.setText("Wrong input");
                    }
                }
            }
            if(name.equals("Admin")) {
                String name1 = userIDField.getText();
                String pass1 = String.valueOf(userPasswordField.getPassword());
                if (name1.isEmpty() || pass1.isEmpty()) {
                    messageLabel.setText("Wrong input");
                }else if (name1.equals("admin") && pass1.equals("admin")){
                    Admin admin = new Admin();
                    frame.dispose();
                }else{
                    messageLabel.setText("Wrong input");
                }
            }
        }

    }
}
