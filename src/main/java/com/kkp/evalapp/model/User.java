package com.kkp.evalapp.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Integer       id         ;
    private LocalDateTime createdAt  ;
    private String        nama       ;
    private Integer       divId      ;
    private Integer       deptId     ;
    private Integer       positionId ;
    private Integer       levelId    ;
    private String        email      ;
    private String        telNo      ;
    private String        password   ;

}
