package com.kkp.evalapp.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.kkp.evalapp.config.Router;
import com.kkp.evalapp.model.Simple;
import com.kkp.evalapp.model.User;
import com.kkp.evalapp.service.UserService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
    private ComboBox<Simple> boxDepartement;

    @FXML
    private ComboBox<Simple> boxDivision;

    @FXML
    private ComboBox<Simple> boxLevel;

    @FXML
    private ComboBox<Simple> boxPosition;

    @FXML
    private Button btnBackToLogin;

    @FXML
    private Button btnRegister;

    @FXML
    private Label lblError;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtIdImployee;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtNoHp;

    @FXML
    private PasswordField txtPass;

    @FXML
    private PasswordField txtRePass;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Simple> positions    = userService.getPositions();
        List<Simple> divisions    = userService.getDivisions();
        List<Simple> departements = userService.getDepartements();
        List<Simple> levels       = userService.getLevels();

        // display the list of countries
        boxPosition.getItems().addAll(positions);
        // boxPosition.setConverter((<Simple> a)->a.getName());
        boxDivision.getItems().addAll(divisions);
        boxDepartement.getItems().addAll(departements);
        boxLevel.getItems().addAll(levels);
    }

    private boolean isCompleteFill(){
        ArrayList<Simple> boxesSimple = new ArrayList<>();
        boxesSimple.add(boxDepartement.getValue());
        boxesSimple.add(boxDivision.getValue());
        boxesSimple.add(boxLevel.getValue());
        boxesSimple.add(boxPosition.getValue());

        for (Simple item : boxesSimple) {
            if(item.getName().isEmpty()){
                lblError.setText("Please complete all the form");
                return false;
            }
        }

        ArrayList<TextField> txtList = new ArrayList<>();
        txtList.add(txtFullName);
        txtList.add(txtIdImployee);
        txtList.add(txtMail);
        txtList.add(txtNoHp);
        txtList.add(txtPass);
        txtList.add(txtRePass);

        // iterate the textField nodes
        for (TextField nodes : txtList) {
            if (nodes.getText().isEmpty()) {
                lblError.setText("Please complete all the form");
                                return false;
            }
        }

        return true;
    }


    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {

        if(!isCompleteFill()){
            return;
        }

        LocalDateTime  date = LocalDateTime.now();
        User newUser = User.builder()
                .id(Integer.valueOf(txtIdImployee.getText()))
                .createdAt(date)
                .nama(txtFullName.getText())
                .divId(boxDivision.getValue().getId())
                .deptId(boxDepartement.getValue().getId())
                .positionId(boxPosition.getValue().getId())
                .levelId(boxLevel.getValue().getId())
                .email(txtMail.getText())
                .telNo(txtNoHp.getText()).build();

        userService.save(newUser);
        lblError.setText("Registaration is succesful");


        
        // if (boxCountry.getSelectionModel().isEmpty()) { // check if a country is selected
        //     lblCountryError.setText("Select a country from the list");
        // } else if (EmailValidator.isValidEmailAddress(txtMail.getText()) == false) { // check if the mail address is a valid address
        //     lblMailError.setText("Enter a valid mail");
        //     lblError.setText("");
        // } else {
        //     lblCountryError.setText("");
        //     lblMailError.setText("");

        //     // store the user's inputs
        //     fullName = txtFullName.getText();
        //     mail = txtMail.getText();
        //     pass = txtPass.getText();
        //     country = boxCountry.getSelectionModel().getSelectedItem().toString();
        //     city = txtCity.getText();

        //     User user = new User();
        //     user.setCity(city);
        //     user.setCountry(country);
        //     user.setEmail(mail);
        //     user.setFullname(fullName);
        //     user.setPassword(pass);
        //     user.setDatecreated(LocalDateTime.now());
        // }

    }

    @FXML
    void onLogin(ActionEvent event) {
        router.navigate(LoginController.class, event);
    }

}
