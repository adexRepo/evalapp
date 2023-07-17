package com.kkp.evalapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportWorkResult {
    private String workResultDepartment    ;
    private Double workResultAverageTarget ;
    private Double workResultAverageRating ;
    private Double workResultAverageResult ;
    private Double workResultAverageQuality;
}
