package org.example.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.Client.Client;
import org.example.Model.SignInModel;
import org.example.Util.Regex;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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

    public AnchorPane root;

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
        String name = TxtUsername.getText();
        try(Connection con = DriverManager.getConnection(URL,props)) {

            String sql = "INSERT INTO user(User_Id, User_name, Password) VALUES(?,?,?)";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, LblUserId.getText());
            pstm.setString(2, TxtUsername.getText());
            pstm.setString(3, TxtPassword.getText());

            int affectrdRows = pstm.executeUpdate();
            if (affectrdRows > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Client Added!!");
            } else {
                new Alert(Alert.AlertType.ERROR, "Error !!");
            }
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ClientForm.fxml"));
        AnchorPane anchorPane = loader.load();
        ClientFormController controller = loader.getController();
        controller.setLblname(name);
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        root.getScene().getWindow().hide();
    }
    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginForm.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        root.getScene().getWindow().hide();
//        go to loginpage
    }

}
