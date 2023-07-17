package com.kkp.evalapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LastCriteriaEvaluation {
    private Integer id              ;
    private Integer quality         ;
    private Double rangeFirst      ;
    private Double rangeLast       ;
    private String description     ;
}
