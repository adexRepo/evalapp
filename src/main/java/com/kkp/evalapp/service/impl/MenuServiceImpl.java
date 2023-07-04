package com.kkp.evalapp.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.kkp.evalapp.mapper.EvalappMapper;
import com.kkp.evalapp.model.MenuItem;
import com.kkp.evalapp.service.MenuService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final SqlSession session;

    @Override
    public List<MenuItem> getAllMenu() {
        return session.getMapper(EvalappMapper.class).selectAllMenu();
    }

}
