package com.kkp.evalapp.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ComponentUi {
    public static void showAlert(AlertType type , String title, String msg) {
		Alert alert = new Alert(type, title+"\n"  + msg );
		alert.showAndWait();
	}
}
