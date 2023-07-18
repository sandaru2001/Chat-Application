package org.example.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Server.ClientHandler;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ServerFormController implements Initializable {
    private final String[] emojis = {
            "\uD83D\uDE00", // 😀
            "\uD83D\uDE01", // 😁
            "\uD83D\uDE02", // 😂
            "\uD83D\uDE03", // 🤣
            "\uD83D\uDE04", // 😄
            "\uD83D\uDE05", // 😅
            "\uD83D\uDE06", // 😆
            "\uD83D\uDE07", // 😇
            "\uD83D\uDE08", // 😈
            "\uD83D\uDE09", // 😉
            "\uD83D\uDE0A", // 😊
            "\uD83D\uDE0B", // 😋
            "\uD83D\uDE0C", // 😌
            "\uD83D\uDE0D", // 😍
            "\uD83D\uDE0E", // 😎
            "\uD83D\uDE0F", // 😏
            "\uD83D\uDE10", // 😐
            "\uD83D\uDE11", // 😑
            "\uD83D\uDE12", // 😒
            "\uD83D\uDE13"  // 😓
    };
    public ImageView Imgselection;
    public ImageView ImgClienLogin;
    public JFXTextField TxtField;
    public JFXButton BtnSend;
    public AnchorPane emojiAnchorPane;
    public GridPane emojiGridPane;
    public VBox vBox;

    public void ImgLoginClientOnAction(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginForm.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setTitle("Add New Client");
        stage.setScene(scene);
        stage.show();
    }
    public void BtnSendOnAction() throws IOException {
        emojiAnchorPane.setVisible(false);
        String text = TxtField.getText();
        if (!Objects.equals(text, "")) {
            SendMsg(text);
        }else {
            ButtonType ok = new ButtonType("Ok");
            ButtonType cancel = new ButtonType("Cancel");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Empty message.Is it ok?",ok,cancel);
            alert.showAndWait();
            ButtonType result =alert.getResult();
            if (result.equals(ok)) {
                SendMsg(text);
            }
        }
    }

    public void SendMsg(String text) throws IOException {
        ClientHandler.broadcast(text);
        HBox hBox = new HBox();
        hBox.setStyle("-fx-alignment: center-right;-fx-fill-height: true;-fx-min-height: 50;-fx-pref-width: 520;-fx-max-width: 520;-fx-padding: 10");
        Label messageLbl = new Label(text);
        messageLbl.setStyle("-fx-background-color:#76ff03;-fx-background-radius:15;-fx-font-size: 18;-fx-font-weight: normal;-fx-text-fill: black;-fx-wrap-text: true;-fx-alignment: center-left;-fx-content-display: left;-fx-padding: 10;-fx-max-width: 350;");
        hBox.getChildren().add(messageLbl);
        vBox.getChildren().add(hBox);
        TxtField.clear();
    }
    public void TxtFieldOnAction(ActionEvent actionEvent) throws IOException {
        BtnSendOnAction();
    }
    public void emojiChooseOnAction(ActionEvent mouseEvent) {
        emojiAnchorPane.setVisible(!emojiAnchorPane.isVisible());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emojiAnchorPane.setVisible(false);
        int buttonIndex = 0;
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                if (buttonIndex < emojis.length) {
                    String emoji = emojis[buttonIndex];
                    JFXButton emojiButton = createEmojiButton(emoji);
                    emojiGridPane.add(emojiButton, column, row);
                    buttonIndex++;
                } else {
                    break;
                }
            }
        }
    }
    public JFXButton createEmojiButton(String emoji) {
        JFXButton button = new JFXButton(emoji);
        button.getStyleClass().add("emoji-button");
        button.setOnAction(this::emojiButtonOnAction);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setFillWidth(button, true);
        GridPane.setFillHeight(button, true);
        button.setStyle("-fx-font-size: 15; -fx-text-fill: black; -fx-background-color: #F0F0F0; -fx-border-radius: 50");
        return button;
    }
    public void emojiButtonOnAction(ActionEvent actionEvent) {
        JFXButton button = (JFXButton) actionEvent.getSource();
        TxtField.appendText(button.getText());
    }
}
