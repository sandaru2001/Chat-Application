package org.example.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.Server.Server;

import java.io.IOException;


public class MainFormController {
    public JFXButton btnStart;

    public void btnStartOnAction(ActionEvent actionEvent) throws IOException {
        startServer();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ServerForm.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Server Form");
        stage.show();
    }

    public void startServer() throws IOException {
        Server server = Server.getServerSocket();
        Thread thread = new Thread(server);
        thread.start();
    }
}
