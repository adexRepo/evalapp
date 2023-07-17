package com.kkp.evalapp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkp.evalapp.mapper.EvalappMapper;
import com.kkp.evalapp.model.EvaluationBase;
import com.kkp.evalapp.model.EvaluationResultAll;
import com.kkp.evalapp.model.SasaranKerja;
import com.kkp.evalapp.model.WorkGoalsBase;
import com.kkp.evalapp.model.WorkGoalsMap;
import com.kkp.evalapp.model.WorkGoalsScale;
import com.kkp.evalapp.service.EvalappServices;
import com.kkp.evalapp.service.WorkGoalsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkGoalsServiceImpl implements WorkGoalsService {

    private final SqlSession session;
    private final EvalappServices service;

    @Override
    public List<WorkGoalsScale> getListScalaWorkGoals() {
        return session.getMapper(EvalappMapper.class).selectAllScaleWorkGoals();
    }

    @Override
    @Transactional
    public Boolean saveWorkGoals(Integer evaluationId, List<SasaranKerja> sasaranKerjaList) {
        if(sasaranKerjaList.size() == 0){
            return false;
        }

        EvaluationBase base = session.getMapper(EvalappMapper.class).selectEvaluationBaseById(evaluationId);
        if(base == null){
            return false;
        }

        List<WorkGoalsScale> lstScale = getListScalaWorkGoals();
        Integer currentId = session.getMapper(EvalappMapper.class).selectCountCurrentIdInWorkGoalsMap();
        Integer nextId = currentId++;
        Integer counter = 1;
        Integer totParamA = 0;
        Double sumResult = 0.0;
        for (SasaranKerja item : sasaranKerjaList) {
            WorkGoalsMap wgm = new WorkGoalsMap();
            wgm.setId(nextId);
            wgm.setNum(counter);
            wgm.setWorkGoals(item.getSasaranKerja());
            wgm.setRealization(item.getRealisasi());
            wgm.setTarget(item.getTarget());
            wgm.setParamA(item.getBobot());
            wgm.setParamB(getIdScale(lstScale,item.getSkalaNilai()));
            wgm.setParamC(item.getHasil());
            session.getMapper(EvalappMapper.class).insertIntoWorkGoalsMap(wgm);
            totParamA = totParamA + item.getBobot();
            sumResult = sumResult + item.getHasil();
            counter++;
        }

        // WorkGoalsBase
        WorkGoalsBase wgb = new WorkGoalsBase();
        wgb.setEvaluationId(evaluationId);
        wgb.setWgmId(nextId);
        wgb.setTotParamA(totParamA);
        wgb.setFinalResultWorkGoal(sumResult);
        session.getMapper(EvalappMapper.class).insertIntoWorkGoalsBase(wgb);

        Integer bobot = 80; // 80 percent default
        // insert into evaluation result all
        var result = new HashMap<>();
        result.put("evaluationId", evaluationId);
        result.put("id", 2); // default value
        result.put("factor", "Total Nilai Sasaran Terbobot"); // default value
        result.put("finalScoreA", sumResult);
        result.put("finalScoreB", bobot);
        result.put("finalWeight", sumResult * bobot);
        session.getMapper(EvalappMapper.class).insertIntoEvaluationResultAll(result);

        List<EvaluationResultAll> resAll = service.checkResultAllAlreadyInsertedOrNot(evaluationId);
        if(resAll.size() == 2){
            service.updateEvaluationBase(evaluationId, resAll);
        }
        return true;
    }

    private Integer getIdScale(List<WorkGoalsScale>  scaleList, Integer score){
        Optional<WorkGoalsScale> scale = scaleList.stream().filter(val-> val.getScore().equals(score)).findFirst();
        if(scale.isPresent()){
            return scale.get().getId();
        }
        return 0;
    }
    
}
