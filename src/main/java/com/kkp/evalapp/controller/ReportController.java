package com.kkp.evalapp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.kkp.evalapp.model.ReportCompetency;
import com.kkp.evalapp.model.ReportIndicator;
import com.kkp.evalapp.model.ReportOverallEvaluation;
import com.kkp.evalapp.model.ReportUser;
import com.kkp.evalapp.model.ReportWorkResult;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/biz/Report.fxml")
public class ReportController  implements Initializable{
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

    
    @Override @FXML
    public void initialize(URL location, ResourceBundle resources) {
        generateReportProperty();
    }

    private void generateReportProperty(){
        idColumn         .setCellValueFactory(new PropertyValueFactory<>("id"         ));
        nameColumn       .setCellValueFactory(new PropertyValueFactory<>("name"       )) ;
        divisionColumn   .setCellValueFactory(new PropertyValueFactory<>("division"   )) ;
        departmentColumn .setCellValueFactory(new PropertyValueFactory<>("department" )) ;
        positionColumn   .setCellValueFactory(new PropertyValueFactory<>("position"   )) ;
        levelColumn      .setCellValueFactory(new PropertyValueFactory<>("level"      )) ;

        indicatorNoColumn          .setCellValueFactory(new PropertyValueFactory<>("indicatorNo"          ));
        indicatorTypeColumn        .setCellValueFactory(new PropertyValueFactory<>("indicatorType"        ));
        indicatorCategoryColumn    .setCellValueFactory(new PropertyValueFactory<>("indicatorCategory"    ));
        indicatorDescriptionColumn .setCellValueFactory(new PropertyValueFactory<>("indicatorDescription" ));

        competencyIdColumn      .setCellValueFactory(new PropertyValueFactory<>("competencyId"      ));
        competencyNameColumn    .setCellValueFactory(new PropertyValueFactory<>("competencyName"    ));
        competencyTargetColumn  .setCellValueFactory(new PropertyValueFactory<>("competencyTarget"  ));
        competencyRatingColumn  .setCellValueFactory(new PropertyValueFactory<>("competencyRating"  ));
        competencyResultColumn  .setCellValueFactory(new PropertyValueFactory<>("competencyResult"  ));
        competencyQualityColumn .setCellValueFactory(new PropertyValueFactory<>("competencyQuality" ));

        workResultDepartmentColumn     .setCellValueFactory(new PropertyValueFactory<>("workResultDepartment"     ));
        workResultAverageTargetColumn  .setCellValueFactory(new PropertyValueFactory<>("workResultAverageTarget"  ));
        workResultAverageRatingColumn  .setCellValueFactory(new PropertyValueFactory<>("workResultAverageRating"  ));
        workResultAverageResultColumn  .setCellValueFactory(new PropertyValueFactory<>("workResultAverageResult"  ));
        workResultAverageQualityColumn .setCellValueFactory(new PropertyValueFactory<>("workResultAverageQuality" ));

        evaluationIdColumn      .setCellValueFactory(new PropertyValueFactory<>("evaluationId"      ));
        userIdColumn            .setCellValueFactory(new PropertyValueFactory<>("userId"            ));
        userNameColumn          .setCellValueFactory(new PropertyValueFactory<>("userName"          ));
        evaluator1NameColumn    .setCellValueFactory(new PropertyValueFactory<>("evaluator1Name"    ));
        evaluator2NameColumn    .setCellValueFactory(new PropertyValueFactory<>("evaluator2Name"    ));
        overallRatingColumn     .setCellValueFactory(new PropertyValueFactory<>("overallRating"     ));
        overallQualityColumn    .setCellValueFactory(new PropertyValueFactory<>("overallQuality"    ));
        evaluationCommentColumn .setCellValueFactory(new PropertyValueFactory<>("evaluationComment" ));
        coachingResultColumn    .setCellValueFactory(new PropertyValueFactory<>("coachingResult"    ));
        addendumColumn          .setCellValueFactory(new PropertyValueFactory<>("addendum"          ));
    }

}
