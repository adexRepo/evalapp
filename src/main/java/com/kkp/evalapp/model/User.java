package com.kkp.evalapp.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long          id         ;
    private String        fullname   ;
    private LocalDateTime datecreated;
    private String        city       ;
    private String        country    ;
    private String        email      ;
    private String        password   ;
}
