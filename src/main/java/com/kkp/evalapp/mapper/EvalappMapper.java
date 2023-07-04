package com.kkp.evalapp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

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

    /* --------------------------------- Insert --------------------------------- */
    void insertNewUser(User entity);
    User selectUserById(String id);
    
    /* --------------------------------- Update --------------------------------- */
    void updatePassword(User entity);

    /* --------------------------------- Delete --------------------------------- */
}
