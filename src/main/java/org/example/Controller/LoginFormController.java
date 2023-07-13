package org.example.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.Client.Client;
import org.example.Model.ClientModel;
import org.example.Model.LoginModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class    LoginFormController implements Initializable {

    public JFXComboBox UsernameCombox;
    public JFXButton btnLogin;
    public JFXButton BtnSignin;
    public JFXPasswordField TxtLoginPassword;
    public AnchorPane root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadClientName();
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException, SQLException {
        String name = (String) UsernameCombox.getSelectionModel().getSelectedItem();
        String password = TxtLoginPassword.getText();
        String pass = ClientModel.checkUser(name);

        if (pass.equalsIgnoreCase(password)) {
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

    private void loadClientName() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> names = LoginModel.getClientName();

            for (String name : names) {
                obList.add(name);
            }
            UsernameCombox.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }
}
