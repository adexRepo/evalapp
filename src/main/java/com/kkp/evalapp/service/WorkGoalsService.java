package com.kkp.evalapp.service;

import java.util.List;

import com.kkp.evalapp.model.SasaranKerja;
import com.kkp.evalapp.model.WorkGoalsScale;

public interface WorkGoalsService {
    List<WorkGoalsScale> getListScalaWorkGoals();

    Boolean saveWorkGoals(Integer evaluationId, List<SasaranKerja> sasaranKerjaList);
}
