package com.kkp.evalapp.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kkp.evalapp.config.Router;
import com.kkp.evalapp.model.ResponseData;
import com.kkp.evalapp.service.UserService;
import com.kkp.evalapp.utils.ComponentUi;
import com.kkp.evalapp.utils.Validator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;

/**
 * @author Odofin Timothy
 */

@Component
@FxmlView("/ui/Login.fxml")
@RequiredArgsConstructor
public class LoginController implements Initializable {
    
    private final UserService userService;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @Autowired
    private Router router;

    @FXML
    void onSignup(ActionEvent event) {

        router.navigate(SignupController.class, event);
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        if(!Validator.isNumber(username.getText())){
            ComponentUi.showAlert(AlertType.ERROR, "Form Registration", "Invalid Employee ID. Please insert a numeric ID with at least 12 characters.");
            return ;
        }

        Integer employeeId = Integer.parseInt(username.getText());
        String pass = password.getText();

        ResponseData<?> res = userService.authenticate(employeeId, pass);

        // if (userService.authenticate(getUsername(), getPassword())) {
        //     // router.navigate(DashboardController.class, event);
        // } else {
        // }
    }

    // private Stage stage;

    public void initialize(URL location, ResourceBundle resources) {
        // this.stage = new Stage();
    }


}
