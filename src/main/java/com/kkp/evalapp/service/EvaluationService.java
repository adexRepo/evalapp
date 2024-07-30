package com.kkp.evalapp.service;

import java.util.List;

import com.kkp.evalapp.model.CreatePlanEvaluation;
import com.kkp.evalapp.model.Employee;

public interface EvaluationService {

    List<Employee> listPenilai(String position);

    List<Employee> listPekerja(String position);

    void createPlanEvaluation(CreatePlanEvaluation planEvaluation);
}
