package com.kkp.evalapp.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupUtil {
    public static void showPopup(Class<?> controllerClass, Object parameter, PopupCallback callback) {
        try {
            // Create a new stage for the popup
            Stage popupStage = new Stage();

            // Load the FXML file and create an instance of the controller class
            FXMLLoader loader = new FXMLLoader(controllerClass.getResource("/path/to/fxml/file.fxml"));
            
            Object controller = loader.getController();

            Parent root = loader.getRoot();

            Scene scene = new Scene(root);

            popupStage.setScene(scene);

            popupStage.initModality(Modality.APPLICATION_MODAL);

            // Show the popup stage
            popupStage.showAndWait();

            // Invoke the callback if provided
            if (callback != null) {
                callback.onPopupClosed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
