package com.kkp.evalapp.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkGoalsMap {
  private Integer id          ;
  private Integer num         ;
  private Date    createdAt   ;
  private Date    updatedAt   ;
  private String  workGoals   ;
  private String  target      ;
  private String  realization ;
  private Integer paramA      ;
  private Integer paramB      ;
  private Double  paramC      ;
}

