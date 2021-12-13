package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EditStudent implements ActionListener {
    Connection con = null;
    public static Student student = new Student();

    JFrame frame = new JFrame();
    JButton backButton = new JButton("Back");
    JButton updateButton = new JButton("Update");
    JButton deleteButton = new JButton("Delete");
    JLabel idLabel = new JLabel(" ID");
    JTextField idText = new JTextField();
    JLabel groupLabel = new JLabel(" Group");
    JTextField groupText = new JTextField();
    JLabel surnameLabel = new JLabel("Surname");
    JTextField surnameText = new JTextField();
    JLabel messageLabel = new JLabel("");
    DefaultListModel listModel = new DefaultListModel();

    EditStudent(){
        backButton.setBounds(315,25,75,25);
        idLabel.setBounds(10,285,30,25);
        idText.setBounds(10,310, 30, 25);
        groupLabel.setBounds(50, 285, 50, 25);
        groupText.setBounds(50, 310, 50, 25);
        surnameLabel.setBounds(110, 285, 75, 25);
        surnameText.setBounds(110,310,75,25);
        updateButton.setBounds(190, 310, 75, 25);
        deleteButton.setBounds(270,310, 75, 25 );
        messageLabel.setBounds(30, 340, 200, 25 );

        String column = String.format("|%-2s|%-25.15s|%-25.15s|%-10.5s|",
                "ID",
                "Name",
                "Surname",
                "Group");
        listModel.addElement(column);
        String column1 = ("--------------------------------------------------------------------------");
        listModel.addElement(column1);

        try {
            String query = "SELECT ID,name,surname,grupe FROM studentas";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String group = rs.getString("grupe");

                student.setId(id);
                student.setName(name);
                student.setSurname(surname);
                student.setGroup(group);
                String info = String.format("|%-2s|%-25.15s|%-25.15s|%-10.5s|",
                        student.getId(),
                        student.getName(),
                        student.getSurname(),
                        student.getGroup());

                listModel.addElement(info);
            }
            JList list = new JList(listModel);
            list.setBounds(10,25, 300,250);
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

        backButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);

        frame.add(backButton);
        frame.add(idLabel);
        frame.add(idText);
        frame.add(groupLabel);
        frame.add(groupText);
        frame.add(updateButton);
        frame.add(deleteButton);
        frame.add(messageLabel);
        frame.add(surnameLabel);
        frame.add(surnameText);

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
        if (e.getSource() == updateButton){
            String id = idText.getText();
            String group = groupText.getText();
            String surname = surnameText.getText();
            if (id.isEmpty() || group.isEmpty() || surname.isEmpty()) {
                messageLabel.setText("Wrong input");
            }else {
                try {
                    int id1 = Integer.parseInt(id);
                    DataBase dataBase = new DataBase();
                    int ats = dataBase.updateStudent(id1, group);
                    if (ats == 0) {
                        messageLabel.setText("Wrong input");
                    } else if (ats == 1) {
                        int ats2 = dataBase.updateStudentGrades(surname, group);
                        if (ats2 == 0){
                            messageLabel.setText("Wrong input");
                        }else if (ats2 == 1){
                            messageLabel.setText("");
                            EditStudent editStudent = new EditStudent();
                            frame.dispose();
                        }
                    }
                } catch (Exception ex) {
                    messageLabel.setText("Wrong input");
                }
            }
        }
        if (e.getSource() == deleteButton){
            String id = idText.getText();
            String group = groupText.getText(); // Reikalauju seip sau, nes noriu :)
            String surname = surnameText.getText();
            if (id.isEmpty() || group.isEmpty() || surname.isEmpty()) {
                messageLabel.setText("Wrong input");
            }else {
                try {
                    int id1 = Integer.parseInt(id);
                    DataBase dataBase = new DataBase();
                    int ats = dataBase.deleteStudent(id1);
                    if (ats == 0) {
                        messageLabel.setText("Wrong input");
                    } else if (ats == 1) {
                        int ats2 = dataBase.deleteStudentGrade(surname);
                        if (ats2 == 0){
                            messageLabel.setText("Wrong input");
                        }else if (ats2 == 1) {
                            messageLabel.setText("");
                            EditStudent editStudent = new EditStudent();
                            frame.dispose();
                        }
                    }
                } catch (Exception ex) {
                    messageLabel.setText("Wrong input");
                }
            }
        }
    }
}
