package com.kkp.evalapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportIndicator {
    private Integer indicatorNo         ;
    private String  indicatorType       ;
    private String  indicatorCategory   ;
    private String  indicatorDescription;
}
