package com.kkp.evalapp.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kkp.evalapp.config.Router;
import com.kkp.evalapp.service.UserService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;

/**
 * @author Odofin Timothy
 */

@Component
@FxmlView("/ui/Login.fxml")
public class LoginController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label lblLogin;

    @Autowired
    private UserService userService;


    @Autowired
    private Router router;

    @FXML
    void onSignup(ActionEvent event) {

        router.navigate(SignupController.class, event);
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        if (userService.authenticate(getUsername(), getPassword())) {
            router.navigate(DashboardController.class, event);

        } else {
            lblLogin.setText("Login Failed.");
        }
    }

    public String getPassword() {
        return password.getText();
    }

    public String getUsername() {
        return username.getText();
    }

    // private Stage stage;

    public void initialize(URL location, ResourceBundle resources) {
        // this.stage = new Stage();
    }


}
