package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.vo.PostVo;

@Service
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;
	
	String namespace = "post.";
	

	public void insertPost(PostVo vo) {	
		sqlSession.insert(namespace + "insert" , vo);					
	}


	public List<PostVo> getPostList(String id, Long categoryNo) {
		System.out.println("POstrepository categoryNo= " + categoryNo);
		Map<String, Object> map = new HashMap();
		map.put("id", id);
		map.put("categoryNo", categoryNo);
		return sqlSession.selectList(namespace + "getPostList", map);
	}


	public PostVo getPostOne(String id, Long categoryNo, Long postNo) {
		System.out.println("POstrepository categoryNo= " + categoryNo);
		Map<String, Object> map = new HashMap();
		map.put("id", id);
		map.put("categoryNo", categoryNo);
		map.put("postNo", postNo);
		return sqlSession.selectOne(namespace + "getPostOne", map);
	}
}
