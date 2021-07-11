package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;

	String namespace = "user.";
	
	public void insertUser(UserVo vo) {	
		sqlSession.insert(namespace + "insert" , vo);					
	}
	public UserVo findByIdAndPassword(String id, String password) {
		Map<String, Object> map = new HashMap();
		map.put("i", id);
		map.put("p", password);
		System.out.println(map.get("i"));
		System.out.println(map.get("p"));
		return sqlSession.selectOne(namespace + "findByIdAndPassword", map);
//		return sqlSession.selectOne(namespace + "findByIdAndPassword", map);
	}
	public UserVo checkId(String id) {
		return sqlSession.selectOne(namespace + "checkId", id);
	}
	
}
