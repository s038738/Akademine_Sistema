package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Teacher implements ActionListener {
    Connection con = null;
    public static Grade gradeBook = new Grade();

    String name = null;
    String teacherSurname = null;

    JFrame frame = new JFrame();
    JButton logoutButton = new JButton("LogOut");
    JLabel helloLabel = new JLabel("");
    JLabel idLabel = new JLabel("Id");
    JLabel surnameLabel = new JLabel("Student surname");
    JLabel groupLabel = new JLabel("Group");
    JLabel subjectLabel = new JLabel("Subject");
    JLabel gradeLabel = new JLabel("Grade");
    JLabel messageLabel = new JLabel("");
    JTextField idText = new JTextField();
    JTextField surnameText = new JTextField();
    JTextField gruopText = new JTextField();
    String choice = "";
    JComboBox choiceList = new JComboBox();
    JTextField gradeText = new JTextField();
    DefaultListModel listModel = new DefaultListModel();
    JButton addButton = new JButton("ADD Grade");
    JButton updateButton = new JButton("Update Grade");
    JButton deleteButton = new JButton("Delete Grade");
    JLabel noteLabel = new JLabel("Note: \"id input only needed when updating or deleting\"");

    Teacher(String name1, String surname){
        name = name1;
        teacherSurname= surname;
        logoutButton.setBounds(300,25,75,25);
        helloLabel.setBounds(50,45,250,25);
        helloLabel.setText("Hello, "+ name + " " + surname);

        idLabel.setBounds(10,365,20,25);
        idText.setBounds(10,395, 20, 25);
        surnameLabel.setBounds(35, 365, 100, 25);
        surnameText.setBounds(35,395, 100, 25 );
        groupLabel.setBounds(140, 365, 50, 25);
        gruopText.setBounds(140, 395, 50, 25);
        subjectLabel.setBounds(195, 365, 75, 25);
        gradeLabel.setBounds(320, 365, 50, 25);
        gradeText.setBounds(320,395, 50, 25);

        addButton.setBounds(15, 440,100,30);
        updateButton.setBounds(120, 440, 120, 30);
        deleteButton.setBounds(245, 440, 120, 30);
        messageLabel.setBounds(15, 475, 200, 30);
        noteLabel.setBounds(10,515,400,30);

        DataBase dataBase = new DataBase();
        choice = dataBase.firstSubject(surname); // Parenka subjecta kad atitiktu comboBox

        String column = String.format("|%-2s|%-25.15s|%-10.10s|%-17.15s|%-2s|",
                "Id",
                "Student",
                "Group",
                "Subject",
                "Grade");
        listModel.addElement(column);

        String column1 = ("--------------------------------------------------------------------------");
        listModel.addElement(column1);

        try {
            String query = "SELECT ID,student,studGroup,subject,grade FROM grades WHERE teacher='"+surname+"'";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String student = rs.getString("student");
                String group = rs.getString("studGroup");
                String subject = rs.getString("subject");
                int grade = rs.getInt("grade");
                gradeBook.setId(id);
                gradeBook.setStudent(student);
                gradeBook.setGroup(group);
                gradeBook.setSubject(subject);
                gradeBook.setGrade(grade);

                String info = String.format("|%-2d|%-25.15s|%-10.10s|%-20.18s|%-2d|",
                        gradeBook.getId(),
                        gradeBook.getStudent(),
                        gradeBook.getGroup(),
                        gradeBook.getSubject(),
                        gradeBook.getGrade() );
                listModel.addElement(info);
            }
            JList list = new JList(listModel);
            list.setBounds(10,105, 400,250);
            frame.add(list);
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
        try {
            String query = "SELECT subject FROM teacher_subject WHERE surname='"+surname+"'";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String subject = rs.getString("subject");
                choiceList.addItem(subject);
            }
            choiceList.setBounds(195,395,120,25);
            choiceList.addActionListener(this);
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

        logoutButton.addActionListener(this);
        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);

        frame.add(logoutButton);
        frame.add(helloLabel);
        frame.add(idLabel);
        frame.add(idText);
        frame.add(surnameLabel);
        frame.add(surnameText);
        frame.add(groupLabel);
        frame.add(gruopText);
        frame.add(subjectLabel);
        frame.add(choiceList);
        frame.add(gradeLabel);
        frame.add(gradeText);
        frame.add(addButton);
        frame.add(messageLabel);
        frame.add(updateButton);
        frame.add(deleteButton);
        frame.add(noteLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,600);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == choiceList) {
            JComboBox cb = (JComboBox) e.getSource();
            choice = (String) cb.getSelectedItem();
        }
        if (e.getSource() == logoutButton) {
            Login login = new Login();
            frame.dispose();
        }
        if (e.getSource() == addButton) {
            String surname = surnameText.getText();
            String group = gruopText.getText();
            String grade = gradeText.getText();
            DataBase dataBase = new DataBase();
            int ats = dataBase.checkStudent(surname, group);

            if (ats == 0) {
                messageLabel.setText("Student does not exist");
            } else if (ats == 1 && !grade.isEmpty()) {
                try {
                    int totalGrade = Integer.parseInt(grade);
                    if (totalGrade <= 10) {
                        int ats1 = dataBase.insertGrade(teacherSurname, surname, group, choice, grade);
                        messageLabel.setText("");

                        if (ats1 == 0) {
                            messageLabel.setText("Wrong input");
                        } else if (ats1 == 1) {
                            messageLabel.setText("");
                            Teacher teacher = new Teacher(name, teacherSurname);
                            frame.dispose();
                        }
                    } else {
                        messageLabel.setText("Grade must be (1-10)");}
                }catch (Exception ed){
                    messageLabel.setText("Wrong input");}
            } else if (grade.isEmpty()) {
                messageLabel.setText("Grade must be (1-10)");}
        }


        if (e.getSource() == updateButton) {
            String id = idText.getText();
            String surname = surnameText.getText();
            String group = gruopText.getText();
            String grade = gradeText.getText();
            DataBase dataBase = new DataBase();
            if (!id.isEmpty()) {
                int ats = dataBase.checkStudent(surname, group);
                if (ats == 0) {
                    messageLabel.setText("Student does not exist");

                } else if (ats == 1 && !grade.isEmpty()) {
                    try {
                        int totalGrade = Integer.parseInt(grade);
                        if (totalGrade <= 10) {
                            try {
                                int id1 = Integer.parseInt(id);
                                int ats1 = dataBase.updateGrade(id1, grade);
                                messageLabel.setText("");
                                if (ats1 == 0) {
                                    messageLabel.setText("Wrong input");

                                } else if (ats1 == 1) {
                                    messageLabel.setText("");
                                    Teacher teacher = new Teacher(name, teacherSurname);
                                    frame.dispose();
                                }
                            } catch (Exception ex) {
                                messageLabel.setText("Wrong input");}
                        } else {
                            messageLabel.setText("Grade must be (1-10)");}
                    } catch (Exception ec) {
                        messageLabel.setText("Wrong input");}
                } else if (grade.isEmpty()) {
                    messageLabel.setText("Grade must be (1-10)");}
            } else {
                messageLabel.setText("Id field is empty");}
        }
        if (e.getSource() == deleteButton) {
            String id = idText.getText();
            String surname = surnameText.getText();
            String group = gruopText.getText();
            DataBase dataBase = new DataBase();
            if (!id.isEmpty()) {
                int ats = dataBase.checkStudent(surname, group);
                if (ats == 0) {
                    messageLabel.setText("Student does not exist");
                } else if (ats == 1) {
                    messageLabel.setText("");
                    try {
                        int id1 = Integer.parseInt(id);
                        int ats1 = dataBase.deleteGrade(id1);
                        if (ats1 == 0) {
                            messageLabel.setText("Wrong input");
                        } else if (ats1 == 1) {
                            messageLabel.setText("");
                            Teacher teacher = new Teacher(name, teacherSurname);
                            frame.dispose();
                        }
                    } catch (Exception ed) {
                        messageLabel.setText("Wrong input");
                    }
                } else {
                    messageLabel.setText("Id field is empty");
                }
            }
        }
    }
}
