package org.example.Model;

import org.example.DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientModel {
    public static boolean checkUser(String userName, String password) throws SQLException {
        boolean valid=false;
        Connection connection = DBConnection.getInstance().getConnection();
        String sql="SELECT * FROM user WHERE User_name= ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,userName);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()){
            String user_password = resultSet.getString(2);
            if(user_password.equals(password)){
                valid=true;
            }
        }
        return valid;
    }
}
