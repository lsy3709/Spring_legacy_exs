package com.spring.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.member.vo.MemberVO;


public class MemberDAOImpl implements MemberDAO {
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		// JdbcTemplate 해당 생성자 인식 못하는 부분 일단 버그. 패스
//		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
		// 해당dataSource 에 있는 디비 연결 관련 정보를 읽을수 없어서 의미가 없음. 
		this.jdbcTemplate = new JdbcTemplate();
	}

	@Override
	public List selectAllMembers() throws DataAccessException {
		
		//현재 위치는 3) MemberDAOImpl 이고, -> 
		String query = "select id,pwd,name,email,joinDate" + " from t_member " + " order by joinDate desc";
		List membersList = new ArrayList();

		membersList = this.jdbcTemplate.query(query, new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO memberVO = new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setPwd(rs.getString("pwd"));
				memberVO.setName(rs.getString("name"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setJoinDate(rs.getDate("joinDate"));
				return memberVO;
			}
		});
		return membersList;
	}

	@Override
	public int addMember(MemberVO memberVO) throws DataAccessException {
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		String name = memberVO.getName();
		String email = memberVO.getEmail();
		String query = "insert into t_member(id,pwd, name,email) values  ("
		                 + "'" + id + "' ,"
	 	                 + "'" + pwd + "' ,"
		                 + "'" + name + "' ,"
		                 + "'" + email + "') ";
		System.out.println(query);
		int result = jdbcTemplate.update(query);
		System.out.println(result);
		return result;
	}

	

}
