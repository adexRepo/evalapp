package com.kkp.evalapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkGoalsBase {
    private Integer evaluationId ;
    private Integer wgmId ;
    private Integer totParamA ;
    private Double finalResultWorkGoal ;
}
