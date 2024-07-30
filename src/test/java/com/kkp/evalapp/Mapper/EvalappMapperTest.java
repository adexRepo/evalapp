package com.kkp.evalapp.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kkp.evalapp.model.Simple;
import com.kkp.evalapp.model.User;
import com.kkp.evalapp.utils.PasswordEncoder;

@SpringBootTest
public class EvalappMapperTest{
    
    @Autowired
    private SqlSession session;

    @Test
    void testGetListInSimple() {
        List<Simple> positions    = session.getMapper(EvalappMapper.class).selectPositionList   ();
        List<Simple> divisions    = session.getMapper(EvalappMapper.class).selectDivisionList   ();
        List<Simple> departements = session.getMapper(EvalappMapper.class).selectDepartementList();
        List<Simple> levels       = session.getMapper(EvalappMapper.class).selectLevelList      ();

        Assertions.assertNotNull(positions   , "Must Not Null");
        Assertions.assertNotNull(divisions   , "Must Not Null");
        Assertions.assertNotNull(departements, "Must Not Null");
        Assertions.assertNotNull(levels      , "Must Not Null");

    }

    @Test
    void testUpdatePasswordUser(){
        String id = "123"; 
        String passwordDefault = "1";

        String encodedPassword = PasswordEncoder.encodePassword(passwordDefault);
    
        System.out.println("encodedPassword : "+encodedPassword);
    
        User req = new User();
        req.setId(Integer.parseInt(id));
        req.setPassword(encodedPassword);
        session.getMapper(EvalappMapper.class).updatePassword      (req);
        System.out.println("encodedPassword : "+encodedPassword);

        // User entity = session.getMapper(EvalappMapper.class).selectUserById(id);
        // System.out.println("entity : "+entity);

        // boolean isMatch = PasswordEncoder.matches(passwordDefault, entity.getPassword());

        // Assertions.assertTrue(isMatch);
    }
    

}
