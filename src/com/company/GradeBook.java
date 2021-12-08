package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GradeBook implements ActionListener {

    Connection con = null;

    public static Grade gradeBook = new Grade();

    String name = null;
    String surname = null;
    String group = null;

    JFrame frame = new JFrame();
    JButton logoutButton = new JButton("LogOut");
    JLabel helloLabel = new JLabel("");
    JLabel messLabel = new JLabel("Grade Book");
    
    DefaultListModel listModel = new DefaultListModel();


    GradeBook(String name1){
        name = name1;
        //System.out.println(name);
        logoutButton.setBounds(300,25,75,25);
        helloLabel.setBounds(50,45,250,25);
        messLabel.setBounds(20,80,100, 25 );

        String column = String.format("|%-40.25s|%-20.18s|%-2s|","Teacher", "Subject","Grade");


        listModel.addElement(column);








        try{
            String query = "SELECT surname,grupe FROM studentas WHERE name='" +name+"'";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                surname = rs.getString("surname");
                group = rs.getString("grupe");
            }
            helloLabel.setText("Hello, "+ name + " " + surname + " " + group);
            frame.add(helloLabel);
            st.close();
        }catch (Exception e){
            System.out.println("Not connected");
        }finally {
            try{
                if (con != null){
                    con.close();
                }
            }catch (SQLException ex) {}
        }

        try {
            String query1 = "SELECT teacher,subject,grade FROM grades WHERE student='"+surname+"' AND studGroup='"+group+"'";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);

            while (rs.next()) {
                String teacher = rs.getString("teacher");
                int grade = rs.getInt("grade");
                String subject = rs.getString("subject");

                gradeBook.setTeacher(teacher);
                gradeBook.setSubject(subject);
                gradeBook.setGrade(grade);
                /*
                String info = String.format("|%-15s|", gradeBook.getTeacher())+"  ,  " +
                        String.format("|%-15s|",gradeBook.getSubject())+"  ,  " +
                        String.format("|%-10d|",gradeBook.getGrade());
*/
                String info = String.format("|%-40.25s|%-20.18s|%-2d|", gradeBook.getTeacher(), gradeBook.getSubject(),gradeBook.getGrade() );

                listModel.addElement(info);
            }
            JList list = new JList(listModel);
            list.setBounds(10,105, 300,250);
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
        logoutButton.addActionListener(this);

        frame.add(logoutButton);
        frame.add(messLabel);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== logoutButton){
            Login login = new Login();
            frame.dispose();

        }
    }
}
