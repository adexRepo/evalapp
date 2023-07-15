package com.kkp.evalapp.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.kkp.evalapp.constats.DataStorage;
import com.kkp.evalapp.model.Competency;
import com.kkp.evalapp.model.CompetencyScale;
import com.kkp.evalapp.model.Items;
import com.kkp.evalapp.model.ScoreMap;
import com.kkp.evalapp.model.TeknikalCompetency;
import com.kkp.evalapp.service.CompetencyService;
import com.kkp.evalapp.utils.ComponentUi;
import com.kkp.evalapp.utils.PopupUtil;

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
public class CompetencyBasicController implements Initializable  {

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
    private Button btnTeknikal;

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
    private List<Items> lstItemsCbb;
    private List<ScoreMap> lstScoreMap;
    private Integer currentIdx;
    private Integer beforeIdx;
    private Integer afterIdx;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lstScoreMap = new ArrayList<>();
        lstCompetency = competencyService.getCompetencyList();
        loadNilai();
        loadCompetencyBasicContent(lstCompetency.get(0));
        btnTeknikal.disableProperty().set(true);
    }

    private void loadCompetencyBasicContent(Competency comp) {
        currentIdx = comp.getNo() - 1;
        beforeIdx = currentIdx - 1;
        afterIdx = currentIdx + 1;

        noTextField.setText(comp.getNo().toString());
        category.setText(comp.getCategory());
        dtlCategory.setText(comp.getDtlCategory());
        String progressCount = comp.getNo() + "/" + lstCompetency.size();
        progressLabel.setText(progressCount);

        List<String> lstItem = new ArrayList<>();
        lstItem.add(comp.getIndicator1() == null ? "" : comp.getIndicator1());
        lstItem.add(comp.getIndicator2() == null ? "" : comp.getIndicator2());
        lstItem.add(comp.getIndicator3() == null ? "" : comp.getIndicator3());
        lstItem.add(comp.getIndicator4() == null ? "" : comp.getIndicator4());
        lstItem.add(comp.getIndicator5() == null ? "" : comp.getIndicator5());
        lstIndicator.getItems().addAll(lstItem);

        if (comp.getNo() != lstCompetency.size()) {
            btnSubmit.disableProperty().set(true);
            btnNext.disableProperty().set(false);
        }

        if (comp.getNo() == 1) {
            btnBack.disableProperty().set(true);
        } else {
            btnBack.disableProperty().set(false);
        }

    }

    private void loadNilai() {
        List<CompetencyScale> lstCompetencyScale = competencyService.getCompetencyScale();

        List<Items> lstItem = new ArrayList<>();
        for (CompetencyScale scale : lstCompetencyScale) {
            Items item = new Items(scale.getScore(), scale.getRemark());
            lstItem.add(item);
        }
        nilaiComboBox.getItems().addAll(lstItem);
        lstItemsCbb = lstItem;
    }

    @FXML
    private void handleBtnNext() {

        Items selectedItem = nilaiComboBox.getValue();
        if (selectedItem == null) {
            ComponentUi.showAlert(AlertType.ERROR, "Form Evaluation",
                    "Cannot proceed to the next step. Please choose a value for 'Nilai' first!");
            return;
        }
        Competency currentComp = lstCompetency.get(currentIdx);
        changeDataCurrent(currentComp, selectedItem.getId());
        Competency nextComp = lstCompetency.get(afterIdx);
        Optional<ScoreMap> score = lstScoreMap.stream()
                .filter(val -> val.getNum().equals(nextComp.getNo()))
                .findFirst();
        doReset();
        if (score.isPresent()) {
            Optional<Items> item = lstItemsCbb.stream()
                    .filter(val -> val.getId().equals(score.get().getScaleId()))
                    .findFirst();
            item.ifPresent(i -> nilaiComboBox.setValue(i));
        }
        if(currentComp.getNo() == lstCompetency.size()){
            btnNext.disableProperty().set(true);
            btnSubmit.disableProperty().set(false);
            btnTeknikal.disableProperty().set(false);
        }else{
            loadCompetencyBasicContent(nextComp);
        }
    }

    private void changeDataCurrent(Competency comp, Integer scale) {
        Optional<ScoreMap> score = lstScoreMap.stream()
                .filter(val -> val.getNum().equals(comp.getNo()))
                .findFirst();
        if (score.isPresent()) {
            if (!(score.get().getScaleId().equals(scale))) {
                lstScoreMap = lstScoreMap.stream()
                        .peek(map -> {
                            if (map.getNum().equals(comp.getNo())) {
                                map.setScaleId(scale);
                            }
                        }).collect(Collectors.toList());
            }
        } else {
            doGetScore(comp, scale);
        }
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
    @SuppressWarnings("unchecked")
    private void handleBtnSubmit() {
        Items selectedItem = nilaiComboBox.getValue();
        if (selectedItem == null) {
            ComponentUi.showAlert(AlertType.ERROR, "Form Evaluation",
                    "Cannot proceed to the Submit Evaluation. Please choose a value for 'Nilai' first!");
            return;
        }
        // save to db
        List<ScoreMap> lstScore = lstScoreMap;
        List<TeknikalCompetency> lstTeknikal = (List<TeknikalCompetency>) DataStorage.getInstance().getCache().get("listTeknikal");
        Integer evaluationId = 1; // for now like this, need to get from first time initate
        competencyService.saveEvaluationCompetency(evaluationId, lstScore, lstTeknikal);
    }

    @FXML
    private void handleBtnBack() {
        Competency beforeComp = lstCompetency.get(beforeIdx);
        Optional<ScoreMap> score = lstScoreMap.stream()
                .filter(val -> val.getNum().equals(beforeComp.getNo()))
                .findFirst();
        doReset();
        if (score.isPresent()) {
            Optional<Items> item = lstItemsCbb.stream()
                    .filter(val -> val.getId().equals(score.get().getScaleId()))
                    .findFirst();
            item.ifPresent(i -> nilaiComboBox.setValue(i));
        }
        loadCompetencyBasicContent(beforeComp);
    }

    @FXML
    private void handleBtnTeknikal() {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("fxmlPath", "/ui/biz/TeknikalCompetency.fxml");
        DataStorage.getInstance().getCache().put("listTeknikal", new ArrayList<TeknikalCompetency>());
        PopupUtil.showPopup(TechnicalCompetencyController.class, parameterMap);
    }

}
