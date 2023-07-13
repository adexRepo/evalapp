package com.kkp.evalapp.service;

import java.util.List;

import com.kkp.evalapp.model.Competency;
import com.kkp.evalapp.model.CompetencyScale;

public interface CompetencyService {
    List<Competency> getCompetencyList ();
    List<CompetencyScale> getCompetencyScale ();
}
