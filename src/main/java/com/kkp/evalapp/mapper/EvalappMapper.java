package com.kkp.evalapp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kkp.evalapp.model.ColumnItem;
import com.kkp.evalapp.model.Competency;
import com.kkp.evalapp.model.CompetencyScale;
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

    /* --------------------------------- Insert --------------------------------- */
    void insertNewUser(User entity);
    
    /* --------------------------------- Update --------------------------------- */
    void updatePassword(User entity);

    /* --------------------------------- Delete --------------------------------- */
}
