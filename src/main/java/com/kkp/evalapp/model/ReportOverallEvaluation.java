package com.kkp.evalapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportOverallEvaluation {
    private Integer evaluationId      ;
    private Integer userId            ;
    private String  userName          ;
    private String  evaluator1Name    ;
    private String  evaluator2Name    ;
    private Double  overallRating     ;
    private Integer overallQuality    ;
    private String  evaluationComment ;
    private String  coachingResult    ;
    private String  addendum          ;
}
