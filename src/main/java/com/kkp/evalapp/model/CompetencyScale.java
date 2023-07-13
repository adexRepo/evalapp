package com.kkp.evalapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetencyScale {
    private Integer id;
    private Integer score;
    private String remark;
}
