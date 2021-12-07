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

}
