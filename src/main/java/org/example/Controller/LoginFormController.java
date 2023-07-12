package org.example.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.Model.ClientModel;

import java.io.IOException;
import java.sql.SQLException;

public class    LoginFormController {

    public JFXComboBox UsernameCombox;
    public JFXButton btnLogin;
    public JFXButton BtnSignin;
    public JFXPasswordField TxtLoginPassword;
    public AnchorPane root;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String name = UsernameCombox.getId();
        String password = TxtLoginPassword.getText();

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
            stage.setTitle("Client Login");
            stage.show();
        }
    }

    public void btnSigninOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SigninForm.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Client SignIn");
        stage.show();
        root.getScene().getWindow().hide();
    }

    public void CmbLoadUsername(ActionEvent actionEvent) {

    }
}
