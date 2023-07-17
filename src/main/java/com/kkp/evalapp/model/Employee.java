package com.kkp.evalapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer idPekerja        ;
    private String  namaPekerja      ;
    private String  divisiPekerja    ;
    private String  departemenPekerja;
    private String  jabatanPekerja   ;
    private String  levelPekerja     ;
}
