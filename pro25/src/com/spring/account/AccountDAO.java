package com.spring.account;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;

public class AccountDAO {
	
	// 현 위치 3번
	// DI 주입
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	// 4번 위치이고, 실젝적인 디비 작업.
	public void updateBalance1() throws DataAccessException {
		sqlSession.update("mapper.account.updateBalance1");
	}
	
	public void updateBalance2() throws DataAccessException {
		sqlSession.update("mapper.account.updateBalance2");
	}
	

}
