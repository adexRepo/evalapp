package com.kkp.evalapp.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkp.evalapp.mapper.EvalappMapper;
import com.kkp.evalapp.model.EvaluationResultAll;
import com.kkp.evalapp.model.LastCriteriaEvaluation;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EvalappServices {

    private SqlSession session;

    public List<EvaluationResultAll> checkResultAllAlreadyInsertedOrNot(Integer evaluationId) {
        return session.getMapper(EvalappMapper.class).selectCurrentEvaluationResultAll(evaluationId);
    }

    @Transactional
    public void updateEvaluationBase(Integer evaluationId, List<EvaluationResultAll> lstResult) {
        try {
            Double finalWeight = 0.0;
            for (EvaluationResultAll item : lstResult) {
                finalWeight = finalWeight + item.getFinalWeight();
            }

            // check scale last criteria
            Integer quality = 0;
            List<LastCriteriaEvaluation> lstLastCriteria = session.getMapper(EvalappMapper.class)
                    .selectAllLastCriteriaEvaluation();
            for (LastCriteriaEvaluation item : lstLastCriteria) {
                if (finalWeight >= item.getRangeFirst() && finalWeight <= item.getRangeLast()) {
                    // between range
                    quality = item.getQuality();
                }
            }

            var upd = new HashMap<>();
            upd.put("id", evaluationId);
            upd.put("finalQuality", quality);
            upd.put("finalWeight", finalWeight);
            session.getMapper(EvalappMapper.class).updateFinalQualityAndWeightInEvaluationBase(upd);
        } catch (Exception e) {
            log.error("Error : updateEvaluationBase", e);
            e.printStackTrace();
        }

    }
}
