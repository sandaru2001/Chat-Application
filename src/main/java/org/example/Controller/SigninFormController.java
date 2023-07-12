package org.example.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.DBConnection.DBConnection;
import org.example.Model.ClientModel;
import org.example.Model.SignInModel;
import org.example.Util.Regex;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class SigninFormController  implements Initializable{

    private final static String URL = "jdbc:mysql://localhost:3306/chatapp";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }
    public JFXTextField TxtUsername;
    public JFXTextField TxtPassword;
    public Label LblUserId;
    public JFXButton btnSave;
    public JFXButton BtnBack;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateNextOrderId();
    }
    public void generateNextOrderId() {
        try {
            String nextId = SignInModel.generateNextOrderId();
            LblUserId.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        //boolean isValidClientId = Regex.isValidClientId(TxtUsername.getText());
        //String id = LblUserId.getText();
        String name = TxtUsername.getText();
        String password = TxtPassword.getText();

        boolean isValid = false;
        try {
            isValid = ClientModel.checkUser(name, password);
        }catch (SQLException e) {
        }
        if (isValid) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ClientForm.fxml"));
            AnchorPane anchorPane = loader.load();
            Scene scene = new Scene(anchorPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
    }
    public void btnBackOnAction(ActionEvent actionEvent) {
    }

}
