package com.kkp.evalapp.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.kkp.evalapp.constats.DataStorage;
import com.kkp.evalapp.model.CompetencyScale;
import com.kkp.evalapp.model.Items;
import com.kkp.evalapp.model.TeknikalCompetency;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/biz/TeknikalCompetency.fxml")
public class TechnicalCompetencyController implements Initializable {

    @FXML
    private TextArea kompetensiTextArea;

    @FXML
    private ComboBox<Items> nilaiComboBox;

    @FXML
    private TableView<TeknikalCompetency> tableTeknikal;

    @FXML
    private TableColumn<TeknikalCompetency, String> nilaiColumn;

    @FXML
    private TableColumn<TeknikalCompetency, String> kompetensiColumn;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    private List<TeknikalCompetency> kompetensiList;

    private List<Items> lstItemsCbb;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the table columns
        nilaiColumn     .setCellValueFactory(new PropertyValueFactory<>("nilai"     ));
        kompetensiColumn.setCellValueFactory(new PropertyValueFactory<>("kompetensi"));
        loadNilai();
        kompetensiList = new ArrayList<>();
        tableTeknikal.setItems(FXCollections.observableList(kompetensiList));
    }

    @SuppressWarnings("unchecked")
    private void loadNilai() {
        DataStorage storage = DataStorage.getInstance();
        List<CompetencyScale> lstCompetencyScale = (List<CompetencyScale>) storage.getCache().get("lstCompetencyScale");

        List<Items> lstItem = new ArrayList<>();
        for (CompetencyScale scale : lstCompetencyScale) {
            Items item = new Items(scale.getScore(), scale.getRemark());
            lstItem.add(item);
        }
        nilaiComboBox.getItems().addAll(lstItem);
        lstItemsCbb = lstItem;
    }

    @FXML
    private void handleAddButton(ActionEvent event) {
        String kompetensi = kompetensiTextArea.getText();
        Items selectedNilai = nilaiComboBox.getValue();

        if (kompetensi != null && selectedNilai != null) {
            TeknikalCompetency newKompetensi = new TeknikalCompetency(selectedNilai.getId(), kompetensi);
            kompetensiList.add(newKompetensi);
            tableTeknikal.setItems(FXCollections.observableList(kompetensiList));
            reset();
        }
    }

    private void reset() {
        nilaiComboBox.setValue(null);
        kompetensiTextArea.setText("");
    }

    @FXML
    private void handleDeleteButton(ActionEvent event) {
        TeknikalCompetency selectedKompetensi = tableTeknikal.getSelectionModel().getSelectedItem();
        if (selectedKompetensi != null) {
            kompetensiList.remove(selectedKompetensi);
            tableTeknikal.refresh();
        }
    }

    @FXML
    private void handleUpdateButton(ActionEvent event) {
        TeknikalCompetency selectedKompetensi = tableTeknikal.getSelectionModel().getSelectedItem();
        if (selectedKompetensi != null) {
            String updatedKompetensi = kompetensiTextArea.getText();
            selectedKompetensi.setKompetensi(updatedKompetensi);
            tableTeknikal.refresh();
        }
    }

    @FXML
    private void handleTableSelection() {
        TeknikalCompetency selectedKompetensi = tableTeknikal.getSelectionModel().getSelectedItem();
        if (selectedKompetensi != null) {
            kompetensiTextArea.setText(selectedKompetensi.getKompetensi());

            Optional<Items> item = lstItemsCbb.stream()
                    .filter(val -> val.getId().equals(selectedKompetensi.getNilai()))
                    .findFirst();
            item.ifPresentOrElse(i -> nilaiComboBox.setValue(i), () -> nilaiComboBox.setValue(null));
        }
    }

    @FXML
    private void handleBtnSubmit() {
        DataStorage storage = DataStorage.getInstance();
        storage.getCache().put("listTeknikal", kompetensiList);

        Stage stage = (Stage) btnSubmit.getScene().getWindow();
        stage.close();
    }

}
