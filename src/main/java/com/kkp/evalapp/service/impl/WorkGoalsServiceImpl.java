package com.kkp.evalapp.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.kkp.evalapp.mapper.EvalappMapper;
import com.kkp.evalapp.model.Items;
import com.kkp.evalapp.service.WorkGoalsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkGoalsServiceImpl implements WorkGoalsService {

    private final SqlSession session;

    @Override
    public List<Items> getListScalaWorkGoals() {
        return session.getMapper(EvalappMapper.class).selectAllScaleWorkGoals();
    }
    
}
