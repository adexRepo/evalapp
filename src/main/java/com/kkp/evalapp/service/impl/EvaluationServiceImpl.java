package com.kkp.evalapp.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkp.evalapp.mapper.EvalappMapper;
import com.kkp.evalapp.model.CreatePlanEvaluation;
import com.kkp.evalapp.model.EvaluationBase;
import com.kkp.evalapp.model.EvaluationRelation;
import com.kkp.evalapp.service.EvaluationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final SqlSession session;

    @Override
    @Transactional
    public void createPlanEvaluation(List<CreatePlanEvaluation> planEvaluation) {
        
        Integer currentId = session.getMapper(EvalappMapper.class).selectCurrentEvaluationBaseId();
        Integer nextId = currentId++;
        for (CreatePlanEvaluation item : planEvaluation) {
            EvaluationBase base = new EvaluationBase();
            base.setId(nextId);
            base.setStartDt(item.getStartDt());
            base.setEndDt(item.getEndDt());
            session.getMapper(EvalappMapper.class).insertNewIntoEvaluationBase();

            EvaluationRelation relation = new EvaluationRelation();
            relation.setEvaluationId(nextId);
            relation.setEvaluatedId(item.getEvaluatedId());
            relation.setEvaluatorId1(item.getEvaluatorId1());
            relation.setEvaluatorId2(item.getEvaluatorId2());
            session.getMapper(EvalappMapper.class).insertNewIntoEvaluationRelation();

            nextId++;
        }
            
    }
    
}
