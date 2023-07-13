package com.kkp.evalapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreMap {
    private Integer id;
    private Integer compMapId;
    private Integer num;
    private Integer scaleId;
}
