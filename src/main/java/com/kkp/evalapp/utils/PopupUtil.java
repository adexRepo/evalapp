package com.kkp.evalapp.utils;

import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupUtil {
    public static void showPopup(Class<?> controllerClass, Map<String, Object>  parameter) {
        try {
            // Create a new stage for the popup
            Stage popupStage = new Stage();

            // Load the FXML file and create an instance of the controller class
            Parent root = FXMLLoader.load(controllerClass.getResource(parameter.get("fxmlPath").toString()));
            Scene scene = new Scene(root);
            popupStage.setScene(scene);
            popupStage.initModality(Modality.APPLICATION_MODAL);

            // Show the popup stage
            popupStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
