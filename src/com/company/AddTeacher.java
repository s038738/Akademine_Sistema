package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTeacher implements ActionListener {

    JFrame frame = new JFrame();
    JButton backButton = new JButton("Back");
    JLabel helloLabel = new JLabel("Register Teacher");
    JButton addButton = new JButton("Add Teacher");
    JLabel nameLabel = new JLabel("Name:");
    JTextField nameText = new JTextField();
    JLabel surnameLabel = new JLabel("Surname:");
    JTextField surnameText = new JTextField();
    JLabel subjectLabel = new JLabel("Subject:");
    JTextField subjectText = new JTextField();
    JLabel messageLabel = new JLabel("");

    AddTeacher(){

        backButton.setBounds(315,25,75,25);
        helloLabel.setBounds(30,25,100,25);
        nameLabel.setBounds(10, 60, 60, 25);
        nameText.setBounds(70, 60, 120, 25);
        surnameLabel.setBounds(10, 90, 60, 25);
        surnameText.setBounds(70,90,120,25);
        subjectLabel.setBounds(10,120, 60,25 );
        subjectText.setBounds(70,120,120,25);
        addButton.setBounds(70,155,120,25);
        messageLabel.setBounds(30,195, 200, 25);

        backButton.addActionListener(this);
        addButton.addActionListener(this);

        frame.add(backButton);
        frame.add(helloLabel);
        frame.add(nameLabel);
        frame.add(nameText);
        frame.add(surnameLabel);
        frame.add(surnameText);
        frame.add(subjectLabel);
        frame.add(subjectText);
        frame.add(addButton);
        frame.add(messageLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton){
            Admin admin = new Admin();
            frame.dispose();
        }
        if(e.getSource() == addButton){
            String name = nameText.getText();
            String surname = surnameText.getText();
            String subject = subjectText.getText();

            if (name.isEmpty() || surname.isEmpty() || subject.isEmpty()){
                messageLabel.setText("Wrong input");
            }else {
                DataBase dataBase = new DataBase();
                int ats = dataBase.insertTeacher(name,surname,subject);
                if (ats == 0){
                    messageLabel.setText("Wrong input");
                }else if (ats == 1){
                    messageLabel.setText("Teacher added");
                }
            }
        }
    }
}

