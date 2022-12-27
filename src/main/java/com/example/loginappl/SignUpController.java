package com.example.loginappl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {


    @FXML
    private Button button_sign_up;
    @FXML
    private Button button_log_in;

    @FXML
    private TextField tf_favChef;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_sign_up.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()){         //checks if input inform is appropiate
                    DBUtils.signUpUser(event,tf_username.getText(),tf_password.getText(),tf_favChef.getText());
                } else{

                    System.out.println("Please fill out all necessary information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill out all necessary information");
                    alert.show();
                }
            }
        });

        button_log_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sample.fxml", "log in!",null, null);
            }
        });
    }
}
