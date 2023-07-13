package org.example.Model;

import org.example.DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientModel {
    public static boolean checkUser(String userName, String password) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql="SELECT Password FROM user WHERE User_name = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,userName);
        ResultSet resultSet = pstm.executeQuery();

        System.out.println("OK");
        if(resultSet.next()){
            System.out.println("QQQQQ");
            String user_password = resultSet.getString(1);
            if(user_password.equals(password)){
                System.out.println("Done");
                return true;
            }
        }
        System.out.println("Not Done");
        return false;
    }
}
