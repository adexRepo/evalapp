package com.kkp.evalapp.services;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kkp.evalapp.model.ColumnItem;
import com.kkp.evalapp.service.MenuService;

@SpringBootTest
public class MenuServiceTest {
    
    @Autowired
    private MenuService menuService;

    @Test
    void testGetColumn(){
        List<ColumnItem> lstColumn = menuService.getColumnByTabName("Template Evaluation");
        Assertions.assertEquals(4, lstColumn.size(), "Must Correct");
    }
}
