package com.kkp.evalapp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.kkp.evalapp.constats.DataStorage;
import com.kkp.evalapp.model.Employee;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/biz/MappingEvaluasi.fxml")
public class MappingEvaluationController implements Initializable {

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
        DataStorage storage = DataStorage.getInstance();
        var evaluationId = storage.getCache().get("evaluationId");
        System.out.println("EvaluationId : "+evaluationId);

        generateTableProperty();
    }

    @FXML
    private void handleBtnSubmit() {
        // Handle "Submit" button action
    }


    private void generateTableProperty() {
        // Initialize table columns and data bindings
        idPekerjaColumn         .setCellValueFactory(new PropertyValueFactory<>("idPekerja"         ));
        namaPekerjaColumn       .setCellValueFactory(new PropertyValueFactory<>("namaPekerja"       ));
        divisiPekerjaColumn     .setCellValueFactory(new PropertyValueFactory<>("divisiPekerja"     ));
        departemenPekerjaColumn .setCellValueFactory(new PropertyValueFactory<>("departemenPekerja" ));
        jabatanPekerjaColumn    .setCellValueFactory(new PropertyValueFactory<>("jabatanPekerja"    ));
        levelPekerjaColumn      .setCellValueFactory(new PropertyValueFactory<>("levelPekerja"      ));

        idPenilaiColumn         .setCellValueFactory(new PropertyValueFactory<>("idPekerja"         ));
        namaPenilaiColumn       .setCellValueFactory(new PropertyValueFactory<>("namaPekerja"       ));
        divisiPenilaiColumn     .setCellValueFactory(new PropertyValueFactory<>("divisiPekerja"     ));
        departemenPenilaiColumn .setCellValueFactory(new PropertyValueFactory<>("departemenPekerja" ));
        jabatanPenilaiColumn    .setCellValueFactory(new PropertyValueFactory<>("jabatanPekerja"    ));
        levelPenilaiColumn      .setCellValueFactory(new PropertyValueFactory<>("levelPekerja"      ));
    }

}
