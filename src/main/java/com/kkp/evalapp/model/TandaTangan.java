package com.kkp.evalapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TandaTangan {
   private String penetapanSasaranKerja  ;
   private String pekerjaSasaranKerja    ;
   private String penilai1SasaranKerja   ;
   private String penilai2SasaranKerja   ;
   private String penetapanHasilPenilaian;
   private String pekerjaHasilPenilaian  ;
   private String penilai1HasilPenilaian ;
   private String penilai2HasilPenilaian ;
}
