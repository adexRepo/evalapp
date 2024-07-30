
package com.kkp.evalapp.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.kkp.evalapp.config.Router;
import com.kkp.evalapp.constats.DataStorage;
import com.kkp.evalapp.model.HistoryEvaluation;
import com.kkp.evalapp.model.TandaTangan;
import com.kkp.evalapp.utils.PopupUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/biz/ManagemenEvaluasi.fxml")
@RequiredArgsConstructor
public class EvaluationMgmtController implements Initializable {

    private final Router router;

    @FXML
    private TableView<HistoryEvaluation> tbHistory;
    @FXML
    private TableColumn<HistoryEvaluation, Integer> evaluationIdColumn;
    @FXML
    private TableColumn<HistoryEvaluation, String> statusColumn;
    @FXML
    private TableColumn<HistoryEvaluation, String> workerColumn;
    @FXML
    private TableColumn<HistoryEvaluation, String> evaluator1Column;
    @FXML
    private TableColumn<HistoryEvaluation, String> evaluator2Column;
    @FXML
    private TableColumn<HistoryEvaluation, String> periodColumn;

    @FXML
    private Button btnBuat;
    @FXML
    private Button btnDetail;
    @FXML
    private Button btnTtd;
    @FXML
    private Button btnResultAll;

    @FXML
    private TableView<TandaTangan> tbTtd;
    @FXML
    private TableColumn<TandaTangan, String> pekerjaSasaranKerjaColumn;
    @FXML
    private TableColumn<TandaTangan, String> penilai1SasaranKerjaColumn;
    @FXML
    private TableColumn<TandaTangan, String> penilai2SasaranKerjaColumn;
    @FXML
    private TableColumn<TandaTangan, String> pekerjaHasilPenilaianColumn;
    @FXML
    private TableColumn<TandaTangan, String> penilai1HasilPenilaianColumn;
    @FXML
    private TableColumn<TandaTangan, String> penilai2HasilPenilaianColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateTableProperty() ;
    }

    @FXML
    private void handleBtnBuat(ActionEvent event) {
        router.popup(MappingEvaluationController.class,event);
        // Map<String, Object> parameterMap = new HashMap<>();
        // parameterMap.put("fxmlPath", "/ui/biz/MappingEvaluasi.fxml");
        // PopupUtil.showPopup(MappingEvaluationController.class, parameterMap);
    }

    @FXML
    private void handleDetail() {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("fxmlPath", "/ui/biz/MappingEvaluasi.fxml");
        DataStorage storage = DataStorage.getInstance();
        storage.getCache().put("evaluationId", 1);// need to change
        PopupUtil.showPopup(MappingEvaluationController.class, parameterMap);
        // Handle "Lihat Detail" button action
    }

    @FXML
    private void handleBtnTtd() {
        // Handle "Tanda Tangani Rencana" and "Lihat Hasil Keseluruhan" button action
    }

    @FXML
    private void handleBtnResultAll() {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("fxmlPath", "/ui/biz/HasilPenilaian.fxml");
        DataStorage storage = DataStorage.getInstance();
        storage.getCache().put("evaluationId", 1);// need to change
        PopupUtil.showPopup(EvaluationResultController.class, parameterMap);
    }

    private void generateTableProperty() {
        // Initialize table columns and data bindings
        evaluationIdColumn .setCellValueFactory(new PropertyValueFactory<>("evaluationId" ));
        statusColumn       .setCellValueFactory(new PropertyValueFactory<>("status"       ));
        workerColumn       .setCellValueFactory(new PropertyValueFactory<>("worker"       ));
        evaluator1Column   .setCellValueFactory(new PropertyValueFactory<>("evaluator1"   ));
        evaluator2Column   .setCellValueFactory(new PropertyValueFactory<>("evaluator2"   ));
        periodColumn       .setCellValueFactory(new PropertyValueFactory<>("period"       ));

        pekerjaSasaranKerjaColumn    .setCellValueFactory(new PropertyValueFactory<>("pekerjaSasaranKerja" ));
        penilai1SasaranKerjaColumn   .setCellValueFactory(new PropertyValueFactory<>("penilai1SasaranKerja" ));
        penilai2SasaranKerjaColumn   .setCellValueFactory(new PropertyValueFactory<>("penilai2SasaranKerja" ));
        pekerjaHasilPenilaianColumn  .setCellValueFactory(new PropertyValueFactory<>("pekerjaHasilPenilaian" ));
        penilai1HasilPenilaianColumn .setCellValueFactory(new PropertyValueFactory<>("penilai1HasilPenilaian" ));
        penilai2HasilPenilaianColumn .setCellValueFactory(new PropertyValueFactory<>("penilai2HasilPenilaian" ));
    }

}
