package com.kkp.evalapp.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationBase {
    private Integer id       ;
    private Date    createdAt;
    private Date    updatedAt;
    private String  startDt  ;
    private String  endDt    ;
    private String  addendum ;
    private String  toHrd    ;
}
