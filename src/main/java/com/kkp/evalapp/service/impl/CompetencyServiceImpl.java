package com.kkp.evalapp.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.kkp.evalapp.mapper.EvalappMapper;
import com.kkp.evalapp.model.Competency;
import com.kkp.evalapp.model.CompetencyScale;
import com.kkp.evalapp.model.EvaluationBase;
import com.kkp.evalapp.model.ScoreMap;
import com.kkp.evalapp.model.TeknikalCompetency;
import com.kkp.evalapp.service.CompetencyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompetencyServiceImpl implements CompetencyService{
    private final SqlSession session;

    @Override
    public List<Competency> getCompetencyList() {
        return session.getMapper(EvalappMapper.class).selectAllCompetency();
    }

    @Override
    public List<CompetencyScale> getCompetencyScale() {
        return session.getMapper(EvalappMapper.class).selectAllCompetencyScale();
    }

    @Override
    public Boolean saveEvaluationCompetency(Integer evaluationId, List<ScoreMap> competencies, List<TeknikalCompetency> teknikalCompetencies) {
        if(competencies.size() == 0){
            return false;
        }

        EvaluationBase base = session.getMapper(EvalappMapper.class).selectEvaluationBaseById(evaluationId);

        // basic competency
        for (ScoreMap cs : competencies) {
            var csNew = new HashMap<>();
            csNew.put("evaluationId", base.getId());
            csNew.put("compMapId", cs.getCompMapId());
            csNew.put("num", cs.getNum());
            csNew.put("scaleId", cs.getScaleId());
            session.getMapper(EvalappMapper.class).insertIntoCompetencyScoreMap(csNew);
        }

        // teknikal competency
        if(teknikalCompetencies.size() != 0){
            Integer counter = 1;
            for (TeknikalCompetency tc : teknikalCompetencies) {
                var tcNew = new HashMap<>();
                tcNew.put("evaluationId", base.getId());
                tcNew.put("id",counter);
                tcNew.put("description",tc.getKompetensi());
                tcNew.put("scaleId",tc.getNilai());
                session.getMapper(EvalappMapper.class).insertIntoCompetencyTeknikal(tcNew);
                counter++;
            }
        }

        // competency base
        Integer countCompetency = competencies.size() + teknikalCompetencies.size();
        var req = new HashMap<>();
        req.put("evaluationId", base.getId());
        req.put("compMapId", competencies.get(0).getCompMapId());
        Integer sumScoreCompetency = session.getMapper(EvalappMapper.class).selectSumCompetencyBasicAndTeknikal(req);
        Double finalResultCompetency = (double) (sumScoreCompetency / countCompetency);

        var scoreBase = new HashMap<>();
        scoreBase.put("evaluationId", base.getId());
        scoreBase.put("countCompetency", countCompetency);
        scoreBase.put("sumScoreCompetency", sumScoreCompetency);
        scoreBase.put("finalResultCompetency", finalResultCompetency);
        session.getMapper(EvalappMapper.class).insertIntoCompetencyScoreBase(scoreBase);

        Integer bobot = 20; // 20 percent default
        // insert into evaluation result all
        var result = new HashMap<>();
        result.put("evaluationId", base.getId());
        result.put("id", 2); // default value
        result.put("factor", "Nilai Rata-rata Behavioural Competencies"); // default value
        result.put("finalScoreA", finalResultCompetency);
        result.put("finalScoreB", bobot);
        result.put("finalWeight", finalResultCompetency*bobot);
        session.getMapper(EvalappMapper.class).insertIntoEvaluationResultAll(result);


        return true;
    }
}
