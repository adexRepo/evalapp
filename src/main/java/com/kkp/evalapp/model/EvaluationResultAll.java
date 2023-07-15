package com.kkp.evalapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationResultAll {
    private Integer evaluationId      ;
    private Integer id                ;
    private Integer factor            ;
    private Double  finalScoreA       ;
    private Integer finalScoreB       ;
    private Double  finalWeight       ;
    private Integer finalQuality      ;
    private String  commentToEvaluator;
}
