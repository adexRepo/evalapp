package com.kkp.evalapp.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.kkp.evalapp.constats.DataStorage;
import com.kkp.evalapp.model.CreatePlanEvaluation;
import com.kkp.evalapp.model.Employee;
import com.kkp.evalapp.service.EvaluationService;
import com.kkp.evalapp.utils.ComponentUi;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/biz/MappingEvaluasi.fxml")
@RequiredArgsConstructor
public class MappingEvaluationController implements Initializable {

    private final EvaluationService evaluationService;

    @FXML
    private TableView<Employee> tbPekerja;
    @FXML
    private TableColumn<Employee, Integer> idPekerjaColumn;
    @FXML
    private TableColumn<Employee, String> namaPekerjaColumn;
    @FXML
    private TableColumn<Employee, String> divisiPekerjaColumn;
    @FXML
    private TableColumn<Employee, String> departemenPekerjaColumn;
    @FXML
    private TableColumn<Employee, String> jabatanPekerjaColumn;
    @FXML
    private TableColumn<Employee, String> levelPekerjaColumn;

    @FXML
    private TableView<Employee> tbPenilai;
    @FXML
    private TableColumn<Employee, Integer> idPenilaiColumn;
    @FXML
    private TableColumn<Employee, String> namaPenilaiColumn;
    @FXML
    private TableColumn<Employee, String> divisiPenilaiColumn;
    @FXML
    private TableColumn<Employee, String> departemenPenilaiColumn;
    @FXML
    private TableColumn<Employee, String> jabatanPenilaiColumn;
    @FXML
    private TableColumn<Employee, String> levelPenilaiColumn;

    @FXML
    private DatePicker startDt;
    @FXML
    private DatePicker endDt;

    @FXML
    private Button btnSubmit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateTableProperty();
        DataStorage storage = DataStorage.getInstance();
        var evalId = storage.getCache().get("evaluationId");
        if (evalId == null) {
            List<Employee> lstPekerja = evaluationService.listPekerja("pekerja");
            List<Employee> lstPenilai = evaluationService.listPekerja("penilai");
            tbPenilai.setItems(FXCollections.observableList(lstPenilai));
            tbPekerja.setItems(FXCollections.observableList(lstPekerja));
        }
    }

    @FXML
    private void handleBtnSubmit() {
        List<Employee> selectedItemsPenilai = tbPenilai.getSelectionModel().getSelectedItems();
        List<Employee> selectedItemsPekerja = tbPekerja.getSelectionModel().getSelectedItems();
        if (selectedItemsPenilai.size() > 2 || selectedItemsPenilai.size() == 0 || selectedItemsPekerja.size() == 0) {
            ComponentUi.showAlert(AlertType.ERROR, "Form Mapping Evaluation",
                    "Tidak dapat menyimpan mapping. kamu harus memilih 1 Pekerja dan minimal 1 penilai");
            return;
        }
        LocalDate startDts = startDt.getValue();
        LocalDate endDts = endDt.getValue();

        if (startDts == null || endDts == null) {
            ComponentUi.showAlert(AlertType.ERROR, "Form Mapping Evaluation",
                    "Tidak dapat menyimpan mapping. Kamu wajib mengisi Start Period dan Akhir Period");
            return;
        }

        CreatePlanEvaluation req = new CreatePlanEvaluation();
        req.setStartDt(startDts.toString());
        req.setEndDt(endDts.toString());
        req.setEvaluatedId(selectedItemsPekerja.get(0).getIdPekerja());
        req.setEvaluatorId1(selectedItemsPenilai.get(0).getIdPekerja());
        req.setEvaluatorId2(selectedItemsPenilai.get(1).getIdPekerja());

        evaluationService.createPlanEvaluation(req);

    }

    private void generateTableProperty() {
        // Initialize table columns and data bindings
        idPekerjaColumn.setCellValueFactory(new PropertyValueFactory<>("idPekerja"));
        namaPekerjaColumn.setCellValueFactory(new PropertyValueFactory<>("namaPekerja"));
        divisiPekerjaColumn.setCellValueFactory(new PropertyValueFactory<>("divisiPekerja"));
        departemenPekerjaColumn.setCellValueFactory(new PropertyValueFactory<>("departemenPekerja"));
        jabatanPekerjaColumn.setCellValueFactory(new PropertyValueFactory<>("jabatanPekerja"));
        levelPekerjaColumn.setCellValueFactory(new PropertyValueFactory<>("levelPekerja"));

        idPenilaiColumn.setCellValueFactory(new PropertyValueFactory<>("idPekerja"));
        namaPenilaiColumn.setCellValueFactory(new PropertyValueFactory<>("namaPekerja"));
        divisiPenilaiColumn.setCellValueFactory(new PropertyValueFactory<>("divisiPekerja"));
        departemenPenilaiColumn.setCellValueFactory(new PropertyValueFactory<>("departemenPekerja"));
        jabatanPenilaiColumn.setCellValueFactory(new PropertyValueFactory<>("jabatanPekerja"));
        levelPenilaiColumn.setCellValueFactory(new PropertyValueFactory<>("levelPekerja"));
        tbPenilai.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        StringConverter<LocalDate> stringConverter = new StringConverter<>() {
            private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };

        startDt.setConverter(stringConverter);
        endDt.setConverter(stringConverter);

    }

}
