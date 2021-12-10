package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EditTeacher implements ActionListener {
    JFrame frame = new JFrame();
    Connection con = null;
    public static TeacherInfo teacher = new TeacherInfo();

    JButton backButton = new JButton("Back");
    JLabel nameLabel = new JLabel("Name");
    JTextField nameText = new JTextField();
    JLabel surnameLabel = new JLabel("Surname");
    JTextField surnameText = new JTextField();
    JLabel subjectLabel = new JLabel("Subject");
    JTextField subjectText = new JTextField();
    JLabel messageLabel = new JLabel("");

    JButton addSubjectButton = new JButton("Add Subject");
    JButton deleteSubjectButton = new JButton("Delete Subject");
    JButton deleteTeacher = new JButton("Delete teacher");

    DefaultListModel listModel = new DefaultListModel();

    EditTeacher(){
        backButton.setBounds(315,25,75,25);
        nameLabel.setBounds(10,285, 120,25);
        nameText.setBounds(10,310,120,25);
        surnameLabel.setBounds(135, 285, 120,25);
        surnameText.setBounds(135,310,120,25);
        subjectLabel.setBounds(260,285,120,25);
        subjectText.setBounds(260,310,120,25);
        addSubjectButton.setBounds(10,340,120,25);
        deleteSubjectButton.setBounds(135,340,120,25);
        deleteTeacher.setBounds(260,340,120,25);
        messageLabel.setBounds(10,375,200,25);

        String column = String.format("|%-20.15s|%-20.15s|%-15.10s|",
                "Name",
                "Surname",
                "Subject");
        listModel.addElement(column);

        String column1 = ("--------------------------------------------------------------------------");
        listModel.addElement(column1);

        try {
            String query = "SELECT name,surname FROM mokytojas";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                teacher.setSurname(surname);
                try {
                    String query1 = "SELECT subject FROM teacher_subject WHERE surname='"+teacher.getSurname()+"'";
                    con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema", "root", "");
                    Statement st1 = con.createStatement();
                    ResultSet rs1 = st1.executeQuery(query1);
                    while (rs1.next()) {
                        String subject = rs1.getString("subject");
                        teacher.setName(name);
                        teacher.setSurname(surname);
                        teacher.setSubject(subject);
                        String info = String.format("|%-20.15s|%-20.15s|%-15.10s|",
                                teacher.getName(),
                                teacher.getSurname(),
                                teacher.getSubject());

                        listModel.addElement(info);
                    }
                    JList list = new JList(listModel);
                    list.setBounds(10,25, 300,250);
                    frame.add(list);
                    st1.close();
                } catch (SQLException ex) {
                    System.out.println("Not connected");
                } finally {
                    try {
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException e) {

                    }
                }
            }
            st.close();
        } catch (SQLException ex) {
            System.out.println("Not connected");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {

            }
        }

        backButton.addActionListener(this);
        addSubjectButton.addActionListener(this);
        deleteSubjectButton.addActionListener(this);
        deleteTeacher.addActionListener(this);

        frame.add(backButton);
        frame.add(addSubjectButton);
        frame.add(nameLabel);
        frame.add(nameText);
        frame.add(surnameLabel);
        frame.add(surnameText);
        frame.add(subjectLabel);
        frame.add(subjectText);
        frame.add(messageLabel);
        frame.add(deleteSubjectButton);
        frame.add(deleteTeacher);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 460);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton){
            Admin admin = new Admin();
            frame.dispose();
        }
        if (e.getSource() == addSubjectButton){
            String name = nameText.getText();
            String surname = surnameText.getText();
            String subject = subjectText.getText();
            if (name.isEmpty() || surname.isEmpty() || subject.isEmpty()){
                messageLabel.setText("Wrong input");
            }else{
                DataBase dataBase = new DataBase();
                int ats = dataBase.teacherLogin(name,surname);
                if (ats == 0){
                    messageLabel.setText("Teacher does not exist");
                }if (ats == 1){
                    int ats2 = dataBase.insertSubject(surname,subject);
                    if (ats2 == 0){
                        messageLabel.setText("Wrong input");
                    }else if (ats2 == 1){
                        messageLabel.setText("");
                        EditTeacher editTeacher = new EditTeacher();
                        frame.dispose();
                    }
                }
            }
        }
        if (e.getSource() == deleteSubjectButton){
            String name = nameText.getText();
            String surname = surnameText.getText();
            String subject = subjectText.getText();
            if (name.isEmpty() || surname.isEmpty() || subject.isEmpty()){
                messageLabel.setText("Wrong input");
            }else{
                DataBase dataBase = new DataBase();
                int ats = dataBase.teacherLogin(name,surname);
                if (ats == 0){
                    messageLabel.setText("Teacher does not exist");
                }if (ats == 1){
                    int ats2 = dataBase.deleteTeacherSubject(surname,subject);
                    if (ats2 == 0){
                        messageLabel.setText("Wrong input");
                    }else if (ats2 == 1){
                        messageLabel.setText("");
                        EditTeacher editTeacher = new EditTeacher();
                        frame.dispose();
                    }
                }
            }
        }
        if (e.getSource() == deleteTeacher){
            String name = nameText.getText();
            String surname = surnameText.getText();
            if (name.isEmpty() || surname.isEmpty()){
                messageLabel.setText("Wrong input");
            }else{
                DataBase dataBase = new DataBase();
                int ats = dataBase.teacherLogin(name,surname);
                if (ats == 0){
                    messageLabel.setText("Teacher does not exist");
                }if (ats == 1){
                    int ats2 = dataBase.deleteTeacher(name, surname);
                    if (ats2 == 0){
                        messageLabel.setText("Wrong input");
                    }else if (ats2 == 1){
                        messageLabel.setText("");
                        EditTeacher editTeacher = new EditTeacher();
                        frame.dispose();
                    }
                }
            }
        }
    }
}
