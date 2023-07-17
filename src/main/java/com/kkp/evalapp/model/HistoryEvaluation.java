package com.kkp.evalapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryEvaluation {
    private Integer evaluationId;
    private String  status      ;
    private String  worker      ;
    private String  evaluator1  ;
    private String  evaluator2  ;
    private String  period      ;
}
