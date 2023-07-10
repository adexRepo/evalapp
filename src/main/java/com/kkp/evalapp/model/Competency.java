package com.kkp.evalapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString @EqualsAndHashCode(callSuper=false)
public class Competency extends SimpleEntity{
    private Integer no;
    private String category;
    private String dtlCategory;
    private String indicator1;
    private String indicator2;
    private String indicator3;
    private String indicator4;
    private String indicator5;
}
