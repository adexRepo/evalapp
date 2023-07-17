package com.kkp.evalapp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.kkp.evalapp.model.Faktor;
import com.kkp.evalapp.model.PlanDevelopment;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;


@Component
@FxmlView("/ui/biz/HasilPenilaian.fxml")
public class EvaluationResultController implements Initializable{

    @FXML
    private TableView<Faktor> tbFaktor;
    @FXML
    private TableColumn<Faktor, String> faktorColumn;
    @FXML
    private TableColumn<Faktor, Integer> nilaiColumn;
    @FXML
    private TableColumn<Faktor, Integer> bobotColumn;
    @FXML
    private TableColumn<Faktor, Integer> nilaiTerbobotColumn;
    @FXML
    private TextField nilaiAkhir;
    @FXML
    private TextField quality;
    @FXML
    private TextField qualityRemark;
    @FXML
    private TextArea areaComment;
    @FXML
    private TableView<PlanDevelopment> tbPlan;
    @FXML
    private TableColumn<PlanDevelopment, String> sasaranPengembanganColumn;
    @FXML
    private TableColumn<PlanDevelopment, String> metodePengembanganColumn;
    @FXML
    private TableColumn<PlanDevelopment, String> periodePengembanganColumn;
    @FXML
    private TableColumn<PlanDevelopment, String> hasilPengembanganColumn;
    @FXML
    private TextArea areaCoaching;
    @FXML
    private TextArea areaAddendum;
    @FXML
    private Button btnSubmit;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateTableProperty();
    }

    @FXML
    private void handleBtnSubmit() {
        // Handle "Submit" button action
    }

    private void generateTableProperty() {
        // Initialize table columns and data bindings
        faktorColumn        .setCellValueFactory(new PropertyValueFactory<>("faktor"        ));
        nilaiColumn         .setCellValueFactory(new PropertyValueFactory<>("nilai"         ));
        bobotColumn         .setCellValueFactory(new PropertyValueFactory<>("bobot"         ));
        nilaiTerbobotColumn .setCellValueFactory(new PropertyValueFactory<>("nilaiTerbobot" ));

        sasaranPengembanganColumn .setCellValueFactory(new PropertyValueFactory<>("sasaranPengembangan" ));
        metodePengembanganColumn  .setCellValueFactory(new PropertyValueFactory<>("metodePengembangan"  ));
        periodePengembanganColumn .setCellValueFactory(new PropertyValueFactory<>("periodePengembangan" ));
        hasilPengembanganColumn   .setCellValueFactory(new PropertyValueFactory<>("hasilPengembangan"   ));

    }
}

