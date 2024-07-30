package com.kkp.evalapp.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.kkp.evalapp.model.Competency;
import com.kkp.evalapp.model.Employee;
import com.kkp.evalapp.model.ReportCompetency;
import com.kkp.evalapp.model.ReportIndicator;
import com.kkp.evalapp.model.ReportOverallEvaluation;
import com.kkp.evalapp.model.ReportUser;
import com.kkp.evalapp.model.ReportWorkResult;
import com.kkp.evalapp.service.CompetencyService;
import com.kkp.evalapp.service.EvaluationService;
import com.kkp.evalapp.utils.ComponentUi;
import com.kkp.evalapp.utils.GenerateReport;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/biz/Report.fxml")
@RequiredArgsConstructor
public class ReportController implements Initializable {

    private final EvaluationService evaluationService;

    private final CompetencyService competencyService;

    @FXML
    private TableView<ReportUser> userTableView;
    @FXML
    private TableColumn<ReportUser, Integer> idColumn;
    @FXML
    private TableColumn<ReportUser, String> nameColumn;
    @FXML
    private TableColumn<ReportUser, String> divisionColumn;
    @FXML
    private TableColumn<ReportUser, String> departmentColumn;
    @FXML
    private TableColumn<ReportUser, String> positionColumn;
    @FXML
    private TableColumn<ReportUser, String> levelColumn;

    @FXML
    private TableView<ReportIndicator> indicatorTableView;
    @FXML
    private TableColumn<ReportIndicator, Integer> indicatorNoColumn;
    @FXML
    private TableColumn<ReportIndicator, String> indicatorTypeColumn;
    @FXML
    private TableColumn<ReportIndicator, String> indicatorCategoryColumn;
    @FXML
    private TableColumn<ReportIndicator, String> indicatorDescriptionColumn;

    @FXML
    private TableView<ReportCompetency> competencyTableView;
    @FXML
    private TableColumn<ReportCompetency, Integer> competencyIdColumn;
    @FXML
    private TableColumn<ReportCompetency, String> competencyNameColumn;
    @FXML
    private TableColumn<ReportCompetency, String> competencyTargetColumn;
    @FXML
    private TableColumn<ReportCompetency, String> competencyRatingColumn;
    @FXML
    private TableColumn<ReportCompetency, String> competencyResultColumn;
    @FXML
    private TableColumn<ReportCompetency, String> competencyQualityColumn;

    @FXML
    private TableView<ReportWorkResult> workResultTableView;
    @FXML
    private TableColumn<ReportWorkResult, String> workResultDepartmentColumn;
    @FXML
    private TableColumn<ReportWorkResult, Double> workResultAverageTargetColumn;
    @FXML
    private TableColumn<ReportWorkResult, Double> workResultAverageRatingColumn;
    @FXML
    private TableColumn<ReportWorkResult, Double> workResultAverageResultColumn;
    @FXML
    private TableColumn<ReportWorkResult, Double> workResultAverageQualityColumn;

    @FXML
    private TableView<ReportOverallEvaluation> overallEvaluationTableView;
    @FXML
    private TableColumn<ReportOverallEvaluation, Integer> evaluationIdColumn;
    @FXML
    private TableColumn<ReportOverallEvaluation, Integer> userIdColumn;
    @FXML
    private TableColumn<ReportOverallEvaluation, String> userNameColumn;
    @FXML
    private TableColumn<ReportOverallEvaluation, String> evaluator1NameColumn;
    @FXML
    private TableColumn<ReportOverallEvaluation, String> evaluator2NameColumn;
    @FXML
    private TableColumn<ReportOverallEvaluation, Double> overallRatingColumn;
    @FXML
    private TableColumn<ReportOverallEvaluation, Integer> overallQualityColumn;
    @FXML
    private TableColumn<ReportOverallEvaluation, String> evaluationCommentColumn;
    @FXML
    private TableColumn<ReportOverallEvaluation, String> coachingResultColumn;
    @FXML
    private TableColumn<ReportOverallEvaluation, String> addendumColumn;

    private List<ReportUser> lstReportUser;
    private List<ReportIndicator> lstReportIndikator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateReportProperty();
        List<Employee> lstPekerja = evaluationService.listPekerja("pekerja");
        // List<Employee> lstPenilai = evaluationService.listPekerja("penilai");
        lstReportUser = new ArrayList<>();
        for (Employee i : lstPekerja) {
            if(lstReportUser.size() == 16){
                break;
            }
            ReportUser data = new ReportUser();
            data.setId(i.getIdPekerja());
            data.setDepartment(i.getDepartemenPekerja());
            data.setDivision(i.getDivisiPekerja());
            data.setLevel(i.getLevelPekerja());
            data.setName(i.getNamaPekerja());
            data.setPosition(i.getJabatanPekerja());
            lstReportUser.add(data);
        }
        userTableView.getItems().setAll(lstReportUser);
        overallEvaluationTableView.getItems().setAll(ReportDummy.generateDummyData(15));
        competencyTableView.getItems().setAll(ReportDummy.generateDummyDataC());
        workResultTableView.getItems().setAll(ReportDummy.generateDummyDataA());
        List<Competency> lstCompetency = competencyService.getCompetencyList();
        lstReportIndikator = new ArrayList<>();
        for (Competency i : lstCompetency) {
            ReportIndicator indicator = new ReportIndicator();
            indicator.setIndicatorNo(i.getNo());
            indicator.setIndicatorCategory(i.getCategory());
            indicator.setIndicatorDescription(i.getDtlCategory());
            indicator.setIndicatorType(i.getCompMapId().toString());
            lstReportIndikator.add(indicator);
        }
        indicatorTableView.getItems().setAll(lstReportIndikator);
    }


    @FXML
    private void handleButtonPdf1() throws Exception {
        String result = GenerateReport.generate("Karyawan",lstReportUser,"pdf");
        ComponentUi.showAlert(AlertType.INFORMATION, "Generate Report", result); // ok
    }

    @FXML
    private void handleButtonPdf2() throws Exception {
        String result = GenerateReport.generate("kompetensi",lstReportIndikator,"pdf");
        ComponentUi.showAlert(AlertType.INFORMATION, "Generate Report", result); // ok
    }

    @FXML
    private void handleButtonPdf3() throws Exception {
        String result = GenerateReport.generate("hasilPenilaian",ReportDummy.generateDummyDataC(),"pdf");
        ComponentUi.showAlert(AlertType.INFORMATION, "Generate Report", result);
    }

    @FXML
    private void handleButtonPdf4() throws Exception {
        String result = GenerateReport.generate("rataRata",ReportDummy.generateDummyDataA(),"pdf");
        ComponentUi.showAlert(AlertType.INFORMATION, "Generate Report", result); // ok
    }

    @FXML
    private void handleButtonPdf5() throws Exception {
        String result = GenerateReport.generate("reportKeseluruhan",ReportDummy.generateDummyData(15),"pdf");
        ComponentUi.showAlert(AlertType.INFORMATION, "Generate Report", result); // ok
    }

    private void generateReportProperty() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        divisionColumn.setCellValueFactory(new PropertyValueFactory<>("division"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));

        indicatorNoColumn.setCellValueFactory(new PropertyValueFactory<>("indicatorNo"));
        indicatorTypeColumn.setCellValueFactory(new PropertyValueFactory<>("indicatorType"));
        indicatorCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("indicatorCategory"));
        indicatorDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("indicatorDescription"));

        competencyIdColumn.setCellValueFactory(new PropertyValueFactory<>("competencyId"));
        competencyNameColumn.setCellValueFactory(new PropertyValueFactory<>("competencyName"));
        competencyTargetColumn.setCellValueFactory(new PropertyValueFactory<>("competencyTarget"));
        competencyRatingColumn.setCellValueFactory(new PropertyValueFactory<>("competencyRating"));
        competencyResultColumn.setCellValueFactory(new PropertyValueFactory<>("competencyResult"));
        competencyQualityColumn.setCellValueFactory(new PropertyValueFactory<>("competencyQuality"));

        workResultDepartmentColumn.setCellValueFactory(new PropertyValueFactory<>("workResultDepartment"));
        workResultAverageTargetColumn.setCellValueFactory(new PropertyValueFactory<>("workResultAverageTarget"));
        workResultAverageRatingColumn.setCellValueFactory(new PropertyValueFactory<>("workResultAverageRating"));
        workResultAverageResultColumn.setCellValueFactory(new PropertyValueFactory<>("workResultAverageResult"));
        workResultAverageQualityColumn.setCellValueFactory(new PropertyValueFactory<>("workResultAverageQuality"));

        evaluationIdColumn.setCellValueFactory(new PropertyValueFactory<>("evaluationId"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        evaluator1NameColumn.setCellValueFactory(new PropertyValueFactory<>("evaluator1Name"));
        evaluator2NameColumn.setCellValueFactory(new PropertyValueFactory<>("evaluator2Name"));
        overallRatingColumn.setCellValueFactory(new PropertyValueFactory<>("overallRating"));
        overallQualityColumn.setCellValueFactory(new PropertyValueFactory<>("overallQuality"));
        evaluationCommentColumn.setCellValueFactory(new PropertyValueFactory<>("evaluationComment"));
        coachingResultColumn.setCellValueFactory(new PropertyValueFactory<>("coachingResult"));
        addendumColumn.setCellValueFactory(new PropertyValueFactory<>("addendum"));
    }

}
