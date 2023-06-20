package com.kkp.evalapp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EvalappMapper {
    List<Map<String,Object>> selectAllUserBase();
}
