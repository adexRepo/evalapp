package com.kkp.evalapp.services;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kkp.evalapp.model.Simple;
import com.kkp.evalapp.service.UserService;

@SpringBootTest
public class UserServicesTest {

    @Autowired
    UserService userService;

    @Test
    void testUserAttributeCodeCombo(){
        List<Simple> positions    = userService.getPositions();
        List<Simple> divisions    = userService.getDivisions();
        List<Simple> departements = userService.getDepartements();
        List<Simple> levels       = userService.getLevels();

        Assertions.assertNotNull(positions   , "Must Not Null");
        Assertions.assertNotNull(divisions   , "Must Not Null");
        Assertions.assertNotNull(departements, "Must Not Null");
        Assertions.assertNotNull(levels      , "Must Not Null");

        for (Simple item : positions) {
            System.out.println(item.getName());
        }
    }

    public static boolean isNumber(String input) {
        try {
            Long.valueOf(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    @Test
    void testStaticNumber(){
        String num = "89695831597";

        Assertions.assertTrue(isNumber(num));
    }

}
