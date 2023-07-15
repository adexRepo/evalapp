package com.kkp.evalapp.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class EvaluationRelation {
    private Integer evaluationId           ;
    private Integer evaluatedId            ;
    private Integer evaluatorId1           ;
    private Integer evaluatorId2           ;
    private Date    signPlanEvaluatedAt    ;
    private Date    signPlanEvaluator1At   ;
    private Date    signPlanEvaluator2At   ;
    private Date    signResultEvaluatedAt  ;
    private Date    signResultEvaluator1At ;
    private Date    signResultEvaluator2At ;
}
