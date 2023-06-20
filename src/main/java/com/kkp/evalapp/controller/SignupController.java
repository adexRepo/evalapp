package com.kkp.evalapp.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.kkp.evalapp.config.EmailValidator;
import com.kkp.evalapp.config.Router;
import com.kkp.evalapp.constats.Countries;
import com.kkp.evalapp.model.User;
import com.kkp.evalapp.service.UserService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/signup.fxml")
@RequiredArgsConstructor
public class SignupController implements Initializable {

    private final UserService userService;

    private final Router router;

    @FXML
    private TextField txtFullName;
    @FXML
    private Label lblNameError;
    @FXML
    private TextField txtMail;
    @FXML
    private Label lblMailError;
    @FXML
    private TextField txtPass;
    @FXML
    private Label lblPassError;
    @FXML
    private ComboBox<String> boxCountry;
    @FXML
    private Label lblCountryError;
    @FXML
    private TextField txtCity;
    @FXML
    private Label lblCityError;
    @FXML
    private Label lblError; 

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
        String fullName;
        String mail;
        String pass;
        String country;
        String city;

        // Check if any of the text field is empty
        ArrayList<TextField> txtList = new ArrayList<>();
        txtList.add(txtFullName);
        txtList.add(txtMail);
        txtList.add(txtPass);
        txtList.add(txtCity);
        // iterate the textField nodes
        for (TextField nodes : txtList) {
            if (nodes.getText().isEmpty()) {
                lblError.setText("Please complete all the form");
            }

        }
        if (boxCountry.getSelectionModel().isEmpty()) { // check if a country is selected
            lblCountryError.setText("Select a country from the list");
        } else if (EmailValidator.isValidEmailAddress(txtMail.getText()) == false) { // check if the mail address is a valid address
            lblMailError.setText("Enter a valid mail");
            lblError.setText("");
        } else {
            lblCountryError.setText("");
            lblMailError.setText("");

            // store the user's inputs
            fullName = txtFullName.getText();
            mail = txtMail.getText();
            pass = txtPass.getText();
            country = boxCountry.getSelectionModel().getSelectedItem().toString();
            city = txtCity.getText();

            User user = new User();
            user.setCity(city);
            user.setCountry(country);
            user.setEmail(mail);
            user.setFullname(fullName);
            user.setPassword(pass);
            user.setDatecreated(LocalDateTime.now());
            userService.save(user);
            lblError.setText("Registaration is succesful");
        }

    }

    @FXML
    void onLogin(ActionEvent event) {
        router.navigate(LoginController.class, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // display the list of countries
        boxCountry.setItems(Countries.obsList());

    }
}
