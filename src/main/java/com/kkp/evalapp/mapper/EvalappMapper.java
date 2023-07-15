package com.kkp.evalapp.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kkp.evalapp.model.ColumnItem;
import com.kkp.evalapp.model.Competency;
import com.kkp.evalapp.model.CompetencyScale;
import com.kkp.evalapp.model.EvaluationBase;
import com.kkp.evalapp.model.MenuItem;
import com.kkp.evalapp.model.Simple;
import com.kkp.evalapp.model.User;

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

    /* --------------------------------- Insert --------------------------------- */
    void insertNewUser(User entity);
    void insertNewIntoEvaluationBase();
    void insertNewIntoEvaluationRelation();
    void insertIntoCompetencyTeknikal(Map<Object, Object> tcNew);
    void insertIntoCompetencyScoreMap(Map<Object, Object> csNew);
    void insertIntoCompetencyScoreBase(Map<Object, Object> scoreBase);
    void insertIntoEvaluationResultAll(Map<Object, Object> result);

    /* --------------------------------- Update --------------------------------- */
    void updatePassword(User entity);

    /* --------------------------------- Delete --------------------------------- */
}
