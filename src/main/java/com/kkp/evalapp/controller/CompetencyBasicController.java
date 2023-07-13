package com.kkp.evalapp.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kkp.evalapp.model.Competency;
import com.kkp.evalapp.model.CompetencyScale;
import com.kkp.evalapp.model.Items;
import com.kkp.evalapp.model.ScoreMap;
import com.kkp.evalapp.service.CompetencyService;
import com.kkp.evalapp.utils.ComponentUi;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/biz/EvaluasiKompetensiDasar.fxml")
public class CompetencyBasicController implements Initializable {

    @Autowired
    private CompetencyService competencyService;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label titleLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private TextField category;

    @FXML
    private TextArea dtlCategory;

    @FXML
    private ListView<String> lstIndicator;

    @FXML
    private ButtonBar buttonBar;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnBack;

    @FXML
    private TextField noTextField;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label noLabel;

    @FXML
    private Label indicatorLabel;

    @FXML
    private Label nilaiLabel;

    @FXML
    private Label progressLabel;

    @FXML
    private ComboBox<Items> nilaiComboBox;

    private List<Competency> lstCompetency;
    private List<ScoreMap> lstScoreMap;
    private Integer currentContent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lstScoreMap = new ArrayList<>();
        lstCompetency = competencyService.getCompetencyList();
        loadNilai();
        loadCompetencyBasicContent(lstCompetency.get(0));
    }

    private void loadCompetencyBasicContent(Competency comp) {
        currentContent = comp.getNo();
        noTextField.setText(comp.getNo().toString());
        category.setText(comp.getCategory());
        dtlCategory.setText(comp.getDtlCategory());
        String progressCount = currentContent + "/" + lstCompetency.size();
        progressLabel.setText(progressCount);

        List<String> lstItem = new ArrayList<>();
        lstItem.add(comp.getIndicator1() == null ? "" : comp.getIndicator1());
        lstItem.add(comp.getIndicator2() == null ? "" : comp.getIndicator2());
        lstItem.add(comp.getIndicator3() == null ? "" : comp.getIndicator3());
        lstItem.add(comp.getIndicator4() == null ? "" : comp.getIndicator4());
        lstItem.add(comp.getIndicator5() == null ? "" : comp.getIndicator5());
        lstIndicator.getItems().addAll(lstItem);
    }

    private void loadNilai() {
        List<CompetencyScale> lstCompetencyScale = competencyService.getCompetencyScale();

        List<Items> lstItem = new ArrayList<>();
        for (CompetencyScale scale : lstCompetencyScale) {
            Items item = new Items(scale.getScore(), scale.getRemark());
            lstItem.add(item);
        }
        nilaiComboBox.getItems().addAll(lstItem);
    }

    @FXML
    private void handleBtnNext() {

        Items selectedItem = nilaiComboBox.getValue();
        if (selectedItem == null) {
            ComponentUi.showAlert(AlertType.ERROR, "Form Evaluation",
                    "Cannot proceed to the next step. Please choose a value for 'Nilai' first!");
            return;
        }
        Competency comp = lstCompetency.get(0);
        doGetScore(comp, selectedItem.getId());
        doReset();
        if (currentContent == lstCompetency.size()) {
            // do something when finish
        } else {
            Competency tgtNext = lstCompetency.get(currentContent);
            loadCompetencyBasicContent(tgtNext);
        }
        System.out.println(lstScoreMap.toString());
    }

    private void doGetScore(Competency comp, Integer scaleId) {
        ScoreMap score = new ScoreMap();
        score.setCompMapId(comp.getCompMapId());
        score.setNum(comp.getNo());
        score.setScaleId(scaleId);
        lstScoreMap.add(score);
    }

    private void doReset() {
        nilaiComboBox.setValue(null);
        noTextField.setText("");
        category.setText("");
        dtlCategory.setText("");
        lstIndicator.getItems().clear();
    }

    @FXML
    private void handleBtnSubmit() {
        // Add your logic here for the Next button
    }

    @FXML
    private void handleBtnBack() {
        // Add your logic here for the Next button
    }

}
