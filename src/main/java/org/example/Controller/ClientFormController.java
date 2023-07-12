package org.example.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.Client.Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;


public class ClientFormController {
    public VBox VBox;
    private Client client;
    public JFXTextField TxtField;
    public ImageView Imgselection;
    public JFXButton BtnSend;
    public ImageView ImgClienLogin;

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public void BtnSendOnAction(ActionEvent actionEvent) {
        String text = TxtField.getText();
        if (text != null) {
            appendText(text);
        }
    }

    public void imgChooseOnAction(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Image files","*.png","*.jpg","*.jpeg");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null){
            try {
                byte[] bytes = Files.readAllBytes(selectedFile.toPath());
                HBox hBox = new HBox();
                hBox.setStyle("-fx-fill-height: true; -fx-min-height: 50; -fx-pref-width: 520; -fx-max-width: 520; -fx-padding: 10; -fx-alignment: center-right;");

                ImageView imageView = new ImageView(new Image(new FileInputStream(selectedFile)));
                imageView.setStyle("-fx-padding: 10px;");
                imageView.setFitHeight(180);
                imageView.setFitWidth(100);

                hBox.getChildren().addAll(imageView);
                VBox.getChildren().add(hBox);

                client.sendImg(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void ImgLoginClientOnAction(MouseEvent mouseEvent) {

    }

    public void appendText(String msg) {
        HBox hBox = new HBox();
        hBox.setStyle("-fx-alignment: center-right;-fx-fill-height: true;-fx-min-height: 50;-fx-pref-width: 520;-fx-max-width: 520;-fx-padding: 10");
        Label label = new Label(msg);
        label.setStyle("-fx-background-color: #76ff03;-fx-background-radius: 15;-fx-font-size: 18;-fx-font-weight: normal;-fx-text-fill: black;-fx-wrap-text: true;-fx-alignment: center-left;-fx-content-display: left;-fx-padding: 10;-fx-max-width: 350;");
        hBox.getChildren().add(label);
        VBox.getChildren().add(hBox);
    }

    public void writeMsg(String msg){
        HBox hBox = new HBox();
        hBox.setStyle("-fx-alignment: center-left;-fx-fill-height: true;-fx-min-height: 50;-fx-pref-width: 520;-fx-max-width: 520;-fx-padding: 10");
        Label label =new Label(msg);
        label.setStyle("");
        hBox.getChildren().add(label);
        VBox.getChildren().add(hBox);
    }
}
