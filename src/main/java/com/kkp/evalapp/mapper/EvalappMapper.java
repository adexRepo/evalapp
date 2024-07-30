package com.kkp.evalapp.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kkp.evalapp.model.ColumnItem;
import com.kkp.evalapp.model.Competency;
import com.kkp.evalapp.model.CompetencyScale;
import com.kkp.evalapp.model.Employee;
import com.kkp.evalapp.model.EvaluationBase;
import com.kkp.evalapp.model.EvaluationRelation;
import com.kkp.evalapp.model.EvaluationResultAll;
import com.kkp.evalapp.model.LastCriteriaEvaluation;
import com.kkp.evalapp.model.MenuItem;
import com.kkp.evalapp.model.Simple;
import com.kkp.evalapp.model.User;
import com.kkp.evalapp.model.WorkGoalsBase;
import com.kkp.evalapp.model.WorkGoalsMap;
import com.kkp.evalapp.model.WorkGoalsScale;

@Mapper
public interface EvalappMapper {

    /* --------------------------------- Select --------------------------------- */

    List<Map<String,Object>> selectAllUserBase();
    List<Simple> selectPositionList   ();
    List<Simple> selectDepartementList();
    List<Simple> selectLevelList      ();
    List<Simple> selectDivisionList   ();
    List<MenuItem> selectAllMenu   ();
    User selectUserById(String id);
    List<ColumnItem> selectColumnItemsByGridId(Integer gridId);
    List<Competency> selectAllCompetency();
    List<CompetencyScale> selectAllCompetencyScale();
    Integer selectCurrentEvaluationBaseId();
    EvaluationBase selectEvaluationBaseById(Integer evaluationId);
    Integer selectSumCompetencyBasicAndTeknikal(HashMap<Object, Object> req);
    List<WorkGoalsScale> selectAllScaleWorkGoals();
    Integer selectCountCurrentIdInWorkGoalsMap();
    List<EvaluationResultAll> selectCurrentEvaluationResultAll(Integer evaluationId);
    List<LastCriteriaEvaluation> selectAllLastCriteriaEvaluation();
    List<Employee> selectAllEmployeePenilai(String position);

    /* --------------------------------- Insert --------------------------------- */
    void insertNewUser(User entity);
    void insertNewIntoEvaluationBase(EvaluationBase base);
    void insertNewIntoEvaluationRelation(EvaluationRelation relation);
    void insertIntoCompetencyTeknikal(Map<Object, Object> tcNew);
    void insertIntoCompetencyScoreMap(Map<Object, Object> csNew);
    void insertIntoCompetencyScoreBase(Map<Object, Object> scoreBase);
    void insertIntoEvaluationResultAll(Map<Object, Object> result);
    void insertIntoWorkGoalsMap(WorkGoalsMap map);
    void insertIntoWorkGoalsBase(WorkGoalsBase base);

    /* --------------------------------- Update --------------------------------- */
    void updatePassword(User entity);
    void updateFinalQualityAndWeightInEvaluationBase(Map<Object, Object> upd);

    /* --------------------------------- Delete --------------------------------- */
}
