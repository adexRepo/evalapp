package com.kkp.evalapp.service;

import java.util.List;

import com.kkp.evalapp.model.Competency;
import com.kkp.evalapp.model.CompetencyScale;
import com.kkp.evalapp.model.ScoreMap;
import com.kkp.evalapp.model.TeknikalCompetency;

public interface CompetencyService {
    List<Competency> getCompetencyList();

    List<CompetencyScale> getCompetencyScale();

    Boolean saveEvaluationCompetency(Integer evaluationId, List<ScoreMap> competencies,
            List<TeknikalCompetency> teknikalCompetencies);
}
