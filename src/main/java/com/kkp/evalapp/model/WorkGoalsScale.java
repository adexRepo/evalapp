package com.kkp.evalapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkGoalsScale {
    private Integer id         ;
    private Integer score      ;
    private String  category   ;
    private Integer startRange ;
    private Integer endRange   ;
    private String  remark     ;
}
