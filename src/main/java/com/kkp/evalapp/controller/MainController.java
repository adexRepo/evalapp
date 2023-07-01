package com.kkp.evalapp.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.kkp.evalapp.model.MenuItem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/Main.fxml")
@RequiredArgsConstructor
public class MainController implements Initializable {

    @FXML
    private MenuBar menuBar;

    @FXML
    private TreeView<String> tree;

    @FXML
    private ImageView img;

    @FXML
    private TabPane tab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image(getClass().getResourceAsStream("/images/edit.png"));
        img.setImage(image);

        insertDataToTreeView(getDummyData());
        setupTreeViewItemClickHandler();
    }

    private void insertDataToTreeView(List<MenuItem> menuItems) {
        Map<String, TreeItem<String>> treeItemMap = new HashMap<>();
        TreeItem<String> rootItem = new TreeItem<>(); // Create a root item

        for (MenuItem menuItem : menuItems) {
            ImageView imgView = new ImageView(new Image(getClass().getResourceAsStream("/images/edit.png")));

            TreeItem<String> treeItem = new TreeItem<>(menuItem.getName(), imgView);
            treeItemMap.put(menuItem.getId(), treeItem);

            String parentId = menuItem.getParent();
            if (parentId != null && treeItemMap.containsKey(parentId)) {
                TreeItem<String> parentItem = treeItemMap.get(parentId);
                parentItem.getChildren().add(treeItem);
            } else {
                rootItem.getChildren().add(treeItem); // Add to the root item
            }
        }

        tree.setRoot(rootItem); // Set the root of the tree view
    }

    private void setupTreeViewItemClickHandler() {
        tree.setOnMouseClicked(event -> {
            TreeItem<String> selectedItem = tree.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                String itemName = selectedItem.getValue();
                // Open a new tab or perform any other desired action
                openNewTab(itemName);
            }
        });
    }

    private void openNewTab(String tabName) {
        // Check if a tab with the same name already exists
        Optional<Tab> existingTab = tab.getTabs().stream()
                .filter(t -> t.getText().equals(tabName))
                .findFirst();

        if (existingTab.isPresent()) {
            // If the tab already exists, select it
            tab.getSelectionModel().select(existingTab.get());
        } else {
            // Create a new tab
            Tab newTab = new Tab(tabName);
            // Create the content for the new tab
            AnchorPane content = new AnchorPane();
            // Add your desired components to the content pane
            content.getChildren().add(new Label("Content for " + tabName));

            newTab.setContent(content);
            tab.getTabs().add(newTab);
            tab.getSelectionModel().select(newTab);
        }
    }


    private List<MenuItem> getDummyData() {
        List<MenuItem> dummyData = new ArrayList<>();
        // Add your dummy data here
        dummyData.add(new MenuItem("1", null, "key1", "Item 1", "/images/edit.png"));
        dummyData.add(new MenuItem("2", null, "key2", "Item 2", "/images/edit.png"));
        dummyData.add(new MenuItem("3", "1", "key3", "Item 1.1", "/images/edit.png"));
        dummyData.add(new MenuItem("4", "1", "key4", "Item 1.2", "/images/edit.png"));
        dummyData.add(new MenuItem("5", "3", "key5", "Item 1.1.1", "/images/edit.png"));
        dummyData.add(new MenuItem("6", null, "key6", "Item 3", "/images/edit.png"));
        dummyData.add(new MenuItem("7", "6", "key7", "Item 3.1", "/images/edit.png"));
        dummyData.add(new MenuItem("8", "6", "key8", "Item 3.2", "/images/edit.png"));
        return dummyData;
    }
}
