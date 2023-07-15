package com.kkp.evalapp.service;

import java.util.List;

import com.kkp.evalapp.model.CreatePlanEvaluation;

public interface EvaluationService {
    void createPlanEvaluation(List<CreatePlanEvaluation> planEvaluation);
}
