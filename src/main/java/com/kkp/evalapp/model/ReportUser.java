package com.kkp.evalapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportUser {
    private Integer id;
    private String name;
    private String division;
    private String department;
    private String position;
    private String level;
}
