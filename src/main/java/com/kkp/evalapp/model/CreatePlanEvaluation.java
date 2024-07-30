package com.kkp.evalapp.model;

import lombok.Data;

@Data
public class CreatePlanEvaluation {
    private Integer evaluatedId ;
    private Integer evaluatorId1;
    private Integer evaluatorId2;
    private String  startDt     ;
    private String  endDt       ;
}