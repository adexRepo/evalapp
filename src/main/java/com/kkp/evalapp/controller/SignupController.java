package com.kkp.evalapp.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.kkp.evalapp.config.Router;
import com.kkp.evalapp.constats.DataStorage;
import com.kkp.evalapp.model.User;
import com.kkp.evalapp.service.UserService;
import com.kkp.evalapp.utils.Converter;

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
    private ComboBox<String> boxDepartement ;

    @FXML
    private ComboBox<String> boxDivision;

    @FXML
    private ComboBox<String> boxLevel;

    @FXML
    private ComboBox<String> boxPosition;

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
        DataStorage dataStorage = DataStorage.getInstance();
        boxPosition    .setItems((Converter.convertListToObservableList(dataStorage.getPositions   () )) );
        boxDivision    .setItems((Converter.convertListToObservableList(dataStorage.getDivisions   () )) );
        boxDepartement .setItems((Converter.convertListToObservableList(dataStorage.getDepartements() )) );
        boxLevel       .setItems((Converter.convertListToObservableList(dataStorage.getLevels      () )) );
    }


    private boolean isCompleteFill() {
        ArrayList<String> listStrings = new ArrayList<>();
        listStrings.add(boxDepartement.getValue());
        listStrings.add(boxDivision.getValue());
        listStrings.add(boxLevel.getValue());
        listStrings.add(boxPosition.getValue());
        listStrings.add(txtFullName.getText());
        listStrings.add(txtIdImployee.getText());
        listStrings.add(txtMail.getText());
        listStrings.add(txtNoHp.getText());
        listStrings.add(txtPass.getText());
        listStrings.add(txtRePass.getText());

        for (String item : listStrings) {
            if (StringUtils.hasLength(item)) {
                lblError.setText("Please complete all the form");
                return false;
            }
        }

        return true;
    }

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {

        if (!isCompleteFill()) {
            return;
        }

        LocalDateTime date = LocalDateTime.now();
        User newUser = User.builder()
                .id(Integer.valueOf(txtIdImployee.getText()))
                .createdAt(date)
                .nama(txtFullName.getText())
                .divId(Integer.valueOf(boxDivision.getId()))
                .deptId(Integer.valueOf(boxDepartement.getId()) )
                .positionId(Integer.valueOf(boxPosition.getId())  )
                .levelId(Integer.valueOf(boxLevel.getId())  )
                .email(txtMail.getText())
                .telNo(txtNoHp.getText()).build();

        userService.save(newUser);
        lblError.setText("Registaration is succesful");
    }

    @FXML
    void onLogin(ActionEvent event) {
        router.navigate(LoginController.class, event);
    }

}
