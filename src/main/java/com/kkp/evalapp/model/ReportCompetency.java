package com.kkp.evalapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportCompetency {
    private Integer competencyId;
    private String competencyName;
    private String competencyTarget;
    private String competencyRating;
    private String competencyResult;
    private String competencyQuality;
}
