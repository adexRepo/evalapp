package com.kkp.evalapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SasaranKerja {
    private Integer no           ;
    private String  sasaranKerja ;
    private String  target       ;
    private String  realisasi    ;
    private Integer bobot        ;
    private Integer skalaNilai   ;
    private Double  hasil        ;
}
