package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.vo.BlogVo;

@Service
public class BlogRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	String namespace = "blog.";
	
	public void insertBlog(BlogVo vo) {	
		sqlSession.insert(namespace + "insert" , vo);					
	}

	public Map<String, Object> getBlogMain(String id, int categoryCount) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("categoryCount", categoryCount);
		return sqlSession.selectOne(namespace + "getBlogMain" , map);
	}

	public int getFirstCategoryCount(String id) {
		return sqlSession.selectOne(namespace + "getFirstCategoryCount", id);
	}

	public BlogVo getBlog(String id) {
		return sqlSession.selectOne(namespace + "getBlog", id);
	}

	public void updateBlog(BlogVo blogVo) {
		sqlSession.update(namespace + "updateBlog", blogVo);
	}

}
