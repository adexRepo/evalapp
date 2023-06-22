package com.kkp.evalapp.Mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kkp.evalapp.mapper.EvalappMapper;
import com.kkp.evalapp.model.Simple;

@SpringBootTest
public class EvalappMapperTest {
    
    @Autowired
    private SqlSession session;

    @Test
    void testGetListInSimple(){
        List<Simple> positions    = session.getMapper(EvalappMapper.class).selectPositionList   ();
        List<Simple> divisions    = session.getMapper(EvalappMapper.class).selectDivisionList   ();
        List<Simple> departements = session.getMapper(EvalappMapper.class).selectDepartementList();
        List<Simple> levels       = session.getMapper(EvalappMapper.class).selectLevelList      ();

        Assertions.assertNotNull(positions   , "Must Not Null");
        Assertions.assertNotNull(divisions   , "Must Not Null");
        Assertions.assertNotNull(departements, "Must Not Null");
        Assertions.assertNotNull(levels      , "Must Not Null");

    }

}
