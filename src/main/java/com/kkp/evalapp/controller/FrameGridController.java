package com.kkp.evalapp.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.kkp.evalapp.constats.DataStorage;
import com.kkp.evalapp.model.ColumnItem;
import com.kkp.evalapp.model.MenuItem;
import com.kkp.evalapp.model.SimpleEntity;
import com.kkp.evalapp.service.MenuService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/FrameGrid.fxml")
@RequiredArgsConstructor
@Data
public class FrameGridController implements Initializable {

    private final MenuService menuService;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private TableView<SimpleEntity> frameGrid;

    @FXML
    private CrudBarController crudBarController;

    DataStorage storage = DataStorage.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize any necessary components or perform additional setup
        Optional<MenuItem> lstMenu = storage.getMenuItems().stream()
                .filter((val) -> val.getName().equals(storage.getCache())).findFirst();
        List<ColumnItem> columnItems = menuService.getColumnByGridId(lstMenu.get().getGridId());
        setupGrid(columnItems);
    }

    /* ------------------------------- oprational ------------------------------- */

    private void setupGrid(List<ColumnItem> columnItems) {
        // System.out.println(frameGrid.toString());
        // Create and configure the table columns
        frameGrid.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        for (ColumnItem columnItem : columnItems) {
            TableColumn<SimpleEntity, String> column = new TableColumn<>(columnItem.getColumnName());
            column.setCellValueFactory(new PropertyValueFactory<>(columnItem.getCellFactory()));
            column.setResizable(true);
            column.setMinWidth(columnItem.getColumnSize());
            frameGrid.getColumns().add(column);
        }
        frameGrid.setMaxWidth(Double.MAX_VALUE);

        frameGrid.getSelectionModel().selectedItemProperty().addListener((obs,
                oldSelection, newSelection) -> {
            if (newSelection != null) {
                crudBarController.getEditButton().setDisable(false);
                crudBarController.getDeleteButton().setDisable(false);
            } else {
                crudBarController.getEditButton().setDisable(true);
                crudBarController.getDeleteButton().setDisable(true);
            }
        });
        frameGrid.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                crudBarController.handleEditButton();
            }
        });
        frameGrid.setOnMousePressed((event) -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                crudBarController.handleEditButton();
            }
        });
    }
}
