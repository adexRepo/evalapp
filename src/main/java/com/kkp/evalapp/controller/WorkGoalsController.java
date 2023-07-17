package com.kkp.evalapp.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kkp.evalapp.model.Items;
import com.kkp.evalapp.model.SasaranKerja;
import com.kkp.evalapp.model.WorkGoalsScale;
import com.kkp.evalapp.service.WorkGoalsService;
import com.kkp.evalapp.utils.ComponentUi;
import com.kkp.evalapp.utils.Validator;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/biz/SasaranKerja.fxml")
public class WorkGoalsController implements Initializable {

    @Autowired
    private WorkGoalsService workGoalsService;

    @FXML
    private TableView<SasaranKerja> tabelSasaran;

    @FXML
    private TableColumn<SasaranKerja, Integer> noColumn;

    @FXML
    private TableColumn<SasaranKerja, String> sasaranKerjaColumn;

    @FXML
    private TableColumn<SasaranKerja, String> targetColumn;

    @FXML
    private TableColumn<SasaranKerja, String> realisasiColumn;

    @FXML
    private TableColumn<SasaranKerja, Integer> bobotColumn;

    @FXML
    private TableColumn<SasaranKerja, Integer> skalaNilaiColumn;

    @FXML
    private TableColumn<SasaranKerja, Double> hasilColumn;

    @FXML
    private TextArea areaSasaranKerja;

    @FXML
    private TextArea areaTarget;

    @FXML
    private TextArea areaRealisasi;

    @FXML
    private TextField textBobot;

    @FXML
    private ComboBox<Items> nilaiComboBox;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField idKaryawan;

    @FXML
    private Button btnCari;

    private List<SasaranKerja> sasaranKerjaList;

    private List<Items> lstItemsCbb;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the table columns
        noColumn.setCellValueFactory(new PropertyValueFactory<>("no"));
        sasaranKerjaColumn.setCellValueFactory(new PropertyValueFactory<>("sasaranKerja"));
        targetColumn.setCellValueFactory(new PropertyValueFactory<>("target"));
        realisasiColumn.setCellValueFactory(new PropertyValueFactory<>("realisasi"));
        bobotColumn.setCellValueFactory(new PropertyValueFactory<>("bobot"));
        skalaNilaiColumn.setCellValueFactory(new PropertyValueFactory<>("skalaNilai"));
        hasilColumn.setCellValueFactory(new PropertyValueFactory<>("hasil"));
        loadNilai();

        // Initialize the sasaranKerjaList
        sasaranKerjaList = new ArrayList<>();

        // Set the sasaranKerjaList as the data source for the table
        tabelSasaran.setItems(FXCollections.observableList(sasaranKerjaList));
    }

    private void loadNilai() {
        List<WorkGoalsScale> lstScale = workGoalsService.getListScalaWorkGoals();
        List<Items> lstItem = new ArrayList<>();
        for (WorkGoalsScale scale : lstScale) {
            Items item = new Items(scale.getScore(), scale.getRemark());
            lstItem.add(item);
        }
        nilaiComboBox.getItems().addAll(lstItem);
        lstItemsCbb = lstItem;
    }

    @FXML
    private void handleAddButton(ActionEvent event) {
        Items selectedNilai = nilaiComboBox.getValue();
        String sasaran = areaSasaranKerja.getText();
        String target = areaTarget.getText();
        String realisasi = areaRealisasi.getText();
        String bobot = textBobot.getText();

        if (selectedNilai != null && sasaran != null && target != null && realisasi != null && bobot != null) {
            boolean isNumber = Validator.isNumber(bobot);
            if (!isNumber) {
                ComponentUi.showAlert(AlertType.WARNING, "Form Evaluation",
                "Bobot must be number");
                return;
            }
            Integer bobotInt = Integer.parseInt(bobot);
            Double bobotDecimal = (double) bobotInt / 100;
            Integer no = sasaranKerjaList.size() + 1;
            Double result = (double) (bobotDecimal * selectedNilai.getId());
            double roundedNumber = Math.round(result * 100.0) / 100.0;
            SasaranKerja nw = new SasaranKerja();
            nw.setNo(no);
            nw.setSasaranKerja(sasaran);
            nw.setTarget(target);
            nw.setRealisasi(realisasi);
            nw.setBobot(bobotInt);
            nw.setSkalaNilai(selectedNilai.getId());
            nw.setHasil(roundedNumber);
            sasaranKerjaList.add(nw);
            tabelSasaran.setItems(FXCollections.observableList(sasaranKerjaList));
            reset();
        }
    }

    private void reset() {
        nilaiComboBox.setValue(null);
        areaSasaranKerja.setText(null);
        areaTarget.setText(null);
        areaRealisasi.setText(null);
        textBobot.setText(null);
    }

    @FXML
    private void handleDeleteButton(ActionEvent event) {
        SasaranKerja selectedWorkGoals = tabelSasaran.getSelectionModel().getSelectedItem();
        if (selectedWorkGoals != null) {
            sasaranKerjaList.remove(selectedWorkGoals);
            tabelSasaran.refresh();
        }
    }

    @FXML
    private void handleUpdateButton(ActionEvent event) {
        SasaranKerja selectedWorkGoals = tabelSasaran.getSelectionModel().getSelectedItem();
        if (selectedWorkGoals != null) {
            Items selectedNilai = nilaiComboBox.getValue();
            String sasaran = areaSasaranKerja.getText();
            String target = areaTarget.getText();
            String realisasi = areaRealisasi.getText();
            String bobot = textBobot.getText();

            if (selectedNilai != null && sasaran != null && target != null && realisasi != null && bobot != null) {
                boolean isNumber = Validator.isNumber(bobot);
                if (!isNumber) {
                    ComponentUi.showAlert(AlertType.WARNING, "Form Evaluation",
                            "Bobot must be number");
                    return;
                }

                Integer bobotInt = Integer.parseInt(bobot);
                Double bobotDecimal = (double) bobotInt / 100;
                Double result = (double) (bobotDecimal * selectedNilai.getId());
                double roundedNumber = Math.round(result * 100.0) / 100.0;

                selectedWorkGoals.setSasaranKerja(sasaran);
                selectedWorkGoals.setTarget(target);
                selectedWorkGoals.setRealisasi(realisasi);
                selectedWorkGoals.setBobot(bobotInt);
                selectedWorkGoals.setSkalaNilai(selectedNilai.getId());
                selectedWorkGoals.setHasil(roundedNumber);
                tabelSasaran.refresh();
            }
        }
    }

    @FXML
    private void handleTableSelection() {
        SasaranKerja selectedWorkGoals = tabelSasaran.getSelectionModel().getSelectedItem();
        if (selectedWorkGoals != null) {
            areaSasaranKerja.setText(selectedWorkGoals.getSasaranKerja());
            areaTarget.setText(selectedWorkGoals.getTarget());
            areaRealisasi.setText(selectedWorkGoals.getRealisasi());
            textBobot.setText(selectedWorkGoals.getBobot().toString());

            Optional<Items> item = lstItemsCbb.stream()
                    .filter(val -> val.getId().equals(selectedWorkGoals.getSkalaNilai()))
                    .findFirst();
            item.ifPresentOrElse(i -> nilaiComboBox.setValue(i), () -> nilaiComboBox.setValue(null));
        }   
     }

    @FXML
    private void handleSubmitButton(ActionEvent event) {
        reset();
        Integer evaluationId = 1; // default need to change after create mapping
        workGoalsService.saveWorkGoals(evaluationId,sasaranKerjaList);
    }

    @FXML
    private void handleFindButton(ActionEvent event) {
        // Handle the Find button action
    }


}
