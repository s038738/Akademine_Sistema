package com.company;

import java.sql.*;

public class DataBase {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Integer studentLogin(String username, String password) {
        int sk = 0;
        try {
            String query = "SELECT * FROM `studentas` WHERE name=? and surname =?";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema", "root", "");
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()){
                sk = 1;
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return sk;
    }

    public Integer teacherLogin(String username, String password) {
        int sk = 0;
        try {
            String query = "SELECT * FROM `mokytojas` WHERE name=? and surname =?";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema", "root", "");
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()){
                sk = 1;
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return sk;
    }

    public Integer checkStudent(String surname, String group) {
        int sk = 0;
        try {
            String query = "SELECT * FROM `studentas` WHERE surname=? and grupe =?";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema", "root", "");
            pst = con.prepareStatement(query);
            pst.setString(1, surname);
            pst.setString(2, group);
            rs = pst.executeQuery();
            if (rs.next()){
                sk = 1;
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return sk;
    }

    public Integer insertGrade(String teacher, String student, String studGroup, String subject, String grade){
        int sk = 0;
        try {
            String query = "INSERT INTO grades (teacher,student,studGroup,subject,grade) VALUES('" + teacher + "'," +
                    "'" + student + "','" + studGroup + "','" + subject + "','" + grade + "')";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema" +
                    "", "root", "");
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            sk = 1;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return sk;
    }

    public Integer updateGrade(int id, String grade){
        int sk = 0;
        try {
            String query = "UPDATE grades set grade='"+grade+"' where ID='"+id+"'";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema" +
                    "", "root", "");
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            sk = 1;
        } catch (SQLException ex) {
            sk = 0;
            System.out.println(ex);
        }
        return sk;
    }

    public Integer deleteGrade(int id){
        int sk = 0;
        try {
            String query = "delete from grades where ID='"+id+"'";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema" +
                    "", "root", "");
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            sk = 1;
        } catch (SQLException ex) {
            sk = 0;
            System.out.println(ex);
        }
        return sk;
    }

    public Integer updateStudent(int id, String group){
        int sk = 0;
        try {
            String query = "UPDATE studentas set grupe='"+group+"' where ID='"+id+"'";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema" +
                    "", "root", "");
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            sk = 1;
        } catch (SQLException ex) {
            sk = 0;
            System.out.println(ex);
        }
        return sk;
    }


    public Integer deleteStudent(int id){
        int sk = 0;
        try {
            String query = "delete from studentas where ID='"+id+"'";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema" +
                    "", "root", "");
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            sk = 1;
        } catch (SQLException ex) {
            sk = 0;
            System.out.println(ex);
        }
        return sk;
    }

    public Integer updateStudentGrades(String surname, String group){
        int sk = 0;
        try {
            String query = "UPDATE grades set studGroup='"+group+"' where student='"+surname+"'";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema" +
                    "", "root", "");
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            sk = 1;
        } catch (SQLException ex) {
            sk = 0;
            System.out.println(ex);
        }
        return sk;
    }


    public Integer deleteStudentGrade(String surname){
        int sk = 0;
        try {
            String query = "delete from grades where student='"+surname+"'";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema" +
                    "", "root", "");
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            sk = 1;
        } catch (SQLException ex) {
            sk = 0;
            System.out.println(ex);
        }
        return sk;
    }

    public String firstSubject(String surname) {
        String subject = null;
        try {
            String query = "SELECT subject FROM `teacher_subject` WHERE surname='"+surname+"'";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema", "root", "");
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()){
                subject = rs.getString("subject");
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return subject;
    }

    public Integer insertStudent(String name, String surname, String group){
        int sk = 0;
        try {
            String query = "INSERT INTO studentas (name,surname,grupe) VALUES('" + name + "','" + surname + "','" + group + "')";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema" +
                    "", "root", "");
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            sk = 1;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return sk;
    }

    public Integer insertSubject(String surname, String subject){
        int sk = 0;
        try {
            String query = "INSERT INTO teacher_subject (surname,subject) VALUES('" + surname + "','" + subject + "')";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema" +
                    "", "root", "");
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            sk = 1;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return sk;
    }

    public Integer deleteTeacherSubject(String surname, String subject){
        int sk = 0;
        try {
            String query = "delete from teacher_subject where surname='"+surname+"' AND subject='"+subject+"'";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema" +
                    "", "root", "");
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            sk = 1;
        } catch (SQLException ex) {
            sk = 0;
            System.out.println(ex);
        }
        return sk;
    }

    public Integer deleteTeacher(String surname, String name){
        int sk = 0;
        try {
            String query = "delete from mokytojas where name='"+name+"' AND  surname='"+surname+"'";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema" +
                    "", "root", "");
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            try {
                String query1 = "delete from teacher_subject where surname='" + surname + "'";
                con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema" +
                        "", "root", "");
                Statement st1 = con.createStatement();
                st1.executeUpdate(query1);
                con.close();
            }catch (SQLException e){
                System.out.println(e);
            }
            sk = 1;
        } catch (SQLException ex) {
            sk = 0;
            System.out.println(ex);
        }
        return sk;
    }

    public Integer insertTeacher(String name, String surname, String subject){
        int sk = 0;
        try {
            String query = "INSERT INTO mokytojas (name, surname) VALUES('" + name + "','" + surname + "')";
            con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema" +
                    "", "root", "");
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            try {
                String query1 = "INSERT INTO teacher_subject (surname, subject) VALUES('" + surname + "','" + subject + "')";
                con = DriverManager.getConnection("jdbc:mysql://localhost/akademine_sistema" +
                        "", "root", "");
                Statement st1 = con.createStatement();
                st1.executeUpdate(query1);
                con.close();
            }catch (SQLException e){
                System.out.println(e);
            }
            sk = 1;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return sk;
    }
}
