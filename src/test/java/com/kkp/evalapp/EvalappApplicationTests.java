package com.kkp.evalapp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kkp.evalapp.mapper.EvalappMapper;


@SpringBootTest
class EvalappApplicationTests {

	@Autowired
	SqlSession session;

	@Test
	void contextLoads() {
		System.out.println("TESTING UNIT TEST");		
	}

	@Test
	void testSessionConnection(){
		List<Map<String, Object>> a = session.getMapper(EvalappMapper.class).selectAllUserBase();
		System.out.println("Response :");		
		System.out.println(a);		
	}
}
