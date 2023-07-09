package com.kkp.evalapp.controller;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.Data;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/CrudBar.fxml")
@Data
public class CrudBarController {
    
    @FXML
    private Button addButton;
    
    @FXML
    private Button editButton;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private Button printButton;
    
    @FXML
    private void initialize() {
        // Initialize the controller
        editButton.setDisable(true);
        deleteButton.setDisable(true);
        printButton.setDisable(true);
        addButton.setOnAction(event -> handleAddButton());
        editButton.setOnAction(event -> handleEditButton());
        deleteButton.setOnAction(event -> handleDeleteButton());
        printButton.setOnAction(event -> handlePrintButton());
    }
    
    public void handleAddButton() {
        // Handle the Add button action
    }
    
    public void handleEditButton() {
        // Handle the Edit button action
    }
    
    public void handleDeleteButton() {
        // Handle the Delete button action
    }
    
    public void handlePrintButton() {
        // Handle the Print button action
    }

    
    // Add any additional methods or event handlers as needed
}
