package edu.ijse.manohandcraft.Controller;

import edu.ijse.manohandcraft.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginPageController {

    @FXML
    private AnchorPane ancMainPage;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;


    @FXML
    void btnLoginOnAction(ActionEvent actionEvent) {
        String inputUserName = txtUserName.getText().trim();
        String inputPassword = txtPassword.getText().trim();
        System.out.println("Username entered: " + inputUserName);
        System.out.println("Password entered: " + inputPassword);

        if (inputUserName.isEmpty() || inputPassword.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all fields.").show();
            return;
        }

        try {
            Connection conn = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, inputUserName);
            stmt.setString(2, inputPassword);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (Objects.equals(inputUserName, "manodya")) {
                    ancMainPage.getChildren().clear();
                    AnchorPane load = FXMLLoader.load(getClass().getResource("/view/DashboardO.fxml"));
                    ancMainPage.getChildren().add(load);
                } else if (Objects.equals(inputUserName, "lasitha")) {
                    ancMainPage.getChildren().clear();
                    AnchorPane load = FXMLLoader.load(getClass().getResource("/view/DashboardS.fxml"));
                    ancMainPage.getChildren().add(load);
                }
            } else {



                new Alert(Alert.AlertType.ERROR, "Invalid username or password!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database error!").show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


