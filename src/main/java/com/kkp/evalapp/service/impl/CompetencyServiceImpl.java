package com.kkp.evalapp.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.kkp.evalapp.mapper.EvalappMapper;
import com.kkp.evalapp.model.Competency;
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
}
