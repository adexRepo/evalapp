package com.kkp.evalapp.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.kkp.evalapp.model.ColumnItem;
import com.kkp.evalapp.model.SimpleEntity;
import com.kkp.evalapp.service.MenuService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/FrameGrid.fxml")
@RequiredArgsConstructor
public class FrameGridController implements Initializable {

    private final MenuService menuService;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private TableView<SimpleEntity> frameGrid;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button printButton;

    private String tabName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize any necessary components or perform additional setup
        List<ColumnItem> columnItems = menuService.getColumnByTabName(tabName);
        setupButton();
        setupGrid(columnItems);
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    /* ------------------------------- oprational ------------------------------- */
    private void setupButton(){
        editButton.setDisable(true);
		deleteButton.setDisable(true);
		printButton.setDisable(true);
		addButton.setOnAction((event) -> { addButtonHandleAction(); });
		editButton.setOnAction((event) -> { editButtonHandleAction(); });
		deleteButton.setOnAction((event) -> { deleteButtonHandleAction(); });
		printButton.setOnAction((event) -> { printButtonHandleAction(); });
    }

    private void setupGrid(List<ColumnItem> columnItems) {
        // System.out.println(frameGrid.toString());
        // Create and configure the table columns
        for (ColumnItem columnItem : columnItems) {
            TableColumn<SimpleEntity, String> column = new TableColumn<>(columnItem.getColumnName());
            column.setCellValueFactory(new PropertyValueFactory<SimpleEntity, String>(columnItem.getCellFactory()));
            frameGrid.getColumns().add(column);
        }
        // frameGrid.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        //     if (newSelection != null) {
        //         editButton.setDisable(false);
        //         deleteButton.setDisable(false);
        //     } else {
        //         editButton.setDisable(true);
        //         deleteButton.setDisable(true);
        //     }
        // });
        // frameGrid.setOnKeyPressed(e -> {
        //     if (e.getCode() == KeyCode.ENTER) {
        //         editButtonHandleAction();
        //     }
        // });
        // frameGrid.setOnMousePressed((event) -> {
        //     if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
        //         editButtonHandleAction();
        //     }
        // });
    }

    private void addButtonHandleAction() {
        System.out.println("IM IN ADD");
        // AbstractFxmlView fxmlView = showDialog();
        // CrudController controller = (CrudController) fxmlView.getFxmlLoader().getController();
        // controller.add();
    }

    private void editButtonHandleAction() {
        System.out.println("IM IN ADD");
        // AbstractFxmlView fxmlView = showDialog();
        // CrudController controller = (CrudController) fxmlView.getFxmlLoader().getController();
        // SimpleEntity entity = frameGrid.getSelectionModel().getSelectedItem();
        // controller.render(entity);
    }

    private void deleteButtonHandleAction() {
        // SimpleEntity entity = frameGrid.getSelectionModel().getSelectedItem();
        // frameService.delete(entity.getId());
        // loadData();
    }

    private void printButtonHandleAction() {
        // logger.debug("clicked printButton");
    }
}
