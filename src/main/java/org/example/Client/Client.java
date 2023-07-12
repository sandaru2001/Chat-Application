package org.example.Client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.example.Controller.ClientFormController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

public class Client implements Runnable, Serializable {
    public final String name;
    public final Socket socket;
    public final DataInputStream dataInputStream;
    public final DataOutputStream dataOutputStream;
    public ClientFormController clientFormController;


    public Client(String name) throws IOException {
        this.name = name;

        socket = new Socket("Localhost",1234);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());

        dataOutputStream.writeUTF(name);
        dataOutputStream.flush();

    }

    @Override
    protected void finalize() throws IOException {
        dataInputStream.close();
        dataOutputStream.close();
        socket.close();
    }

    @Override
    public void run() {
        String message = "";
        while (!message.equals("finish")){
            try {
                message = dataInputStream.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendMsg(String msg) throws IOException {
        dataOutputStream.writeUTF(msg);
        dataOutputStream.flush();
    }

    public void sendImg(byte[] bytes) throws IOException {
        dataOutputStream.writeUTF("*image*");
        dataOutputStream.writeInt(bytes.length);
        dataOutputStream.write(bytes);
        dataOutputStream.flush();
    }
    public void loadScene() throws IOException {
        Stage stage =new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ClientForm.fxml"));
        Parent parent = loader.load();
        clientFormController =loader.load();
        clientFormController.setClient(this);
    }
}
