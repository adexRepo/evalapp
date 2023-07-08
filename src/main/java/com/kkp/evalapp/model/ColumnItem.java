package com.kkp.evalapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnItem {
    private String  tabName    ;
    private String  columnName ;
    private Integer columnNo   ;
    private String  cellFactory;
}
