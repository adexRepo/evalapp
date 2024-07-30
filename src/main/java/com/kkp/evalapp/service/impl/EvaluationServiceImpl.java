package com.kkp.evalapp.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkp.evalapp.mapper.EvalappMapper;
import com.kkp.evalapp.model.CreatePlanEvaluation;
import com.kkp.evalapp.model.Employee;
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
    public void createPlanEvaluation(CreatePlanEvaluation planEvaluation) {

        Integer currentId = session.getMapper(EvalappMapper.class).selectCurrentEvaluationBaseId();
        Integer nextId = currentId + 1;
        EvaluationBase base = new EvaluationBase();
        base.setId(nextId);
        base.setStartDt(planEvaluation.getStartDt());
        base.setEndDt(planEvaluation.getEndDt());
        session.getMapper(EvalappMapper.class).insertNewIntoEvaluationBase(base);

        EvaluationRelation relation = new EvaluationRelation();
        relation.setEvaluationId(nextId);
        relation.setEvaluatedId(planEvaluation.getEvaluatedId());
        relation.setEvaluatorId1(planEvaluation.getEvaluatorId1());
        relation.setEvaluatorId2(planEvaluation.getEvaluatorId2());
        session.getMapper(EvalappMapper.class).insertNewIntoEvaluationRelation(relation);

    }

    @Override
    public List<Employee> listPenilai(String position) {
        return session.getMapper(EvalappMapper.class).selectAllEmployeePenilai(position);
    }

    @Override
    public List<Employee> listPekerja(String position) {
        return session.getMapper(EvalappMapper.class).selectAllEmployeePenilai(position);
    }

}
