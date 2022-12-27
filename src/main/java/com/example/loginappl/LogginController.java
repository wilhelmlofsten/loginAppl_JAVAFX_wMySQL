package com.example.loginappl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LogginController implements Initializable {

    @FXML
    private Button button_log_out;

    @FXML
    Label label_welcome_user;

    @FXML
    Label label_favChef;
    @FXML
    Label label_signed_in;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_log_out.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sample.fxml", "Login!", null,null);

            }
        });

    }

    public void set_userInformation(String username, String favChef){

        label_welcome_user.setText("Welcome " + username + "!");
        label_favChef.setText("Good choice with: " + favChef);
    }
}
