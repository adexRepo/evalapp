package com.kkp.evalapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnItem {
    private Integer id          ;
    private Integer typeGrid    ;
    private Integer columnNo    ;
    private String  columnName  ;
    private String  cellFactory ;
    private Integer columnSize  ;
}
