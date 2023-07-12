package org.example.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ServerFormController {
    public ImageView Imgselection;
    public ImageView ImgClienLogin;
    public JFXTextField TxtField;
    public JFXButton BtnSend;

    public void BtnSendOnAction(ActionEvent actionEvent) {
    }

    public void imgChooseOnAction(MouseEvent mouseEvent) throws IOException {
    }

    public void ImgLoginClientOnAction(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginForm.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setTitle("Add New Client");
        stage.setScene(scene);
        stage.show();
    }
}
