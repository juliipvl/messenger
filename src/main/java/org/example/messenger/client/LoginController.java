package org.example.messenger.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.messenger.Main;

import java.net.Socket;
import java.util.regex.Pattern;

public class LoginController {

    @FXML
    private TextField ipAddressField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button startChatButton;

    private final String SERVER_IP = "127.0.0.1"; // Замінити на реальну IP-адресу сервера

    private Socket socket;
    @FXML
    private void initialize() {
        startChatButton.setOnAction(event -> handleNextButton());
    }

    @FXML
    private void handleNextButton() {
        String ip = ipAddressField.getText().trim();
        String username = usernameField.getText().trim();

        if (ip.isEmpty() || username.isEmpty()) {
            showAlert("Помилка", "Будь ласка, введіть IP-адресу та ім’я користувача.");
            return;
        }

        if (!isValidIP(ip)) {
            showAlert("Невірний формат", "IP-адреса має невірний формат.");
            return;
        }

        if (!ip.equals(SERVER_IP)) {
            showAlert("Підключення неможливе", "IP-адреса не відповідає серверу.");
            return;
        }

        Main.setRoot("ChatScreen.fxml");
    }

    private boolean isValidIP(String ip) {
        String regex =
                "^((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}" +
                        "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)$";
        return Pattern.matches(regex, ip);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
