package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;
	
	String namespace = "category.";
	

	public void insertCategory(CategoryVo categoryVo) {	
		sqlSession.insert(namespace + "insert" , categoryVo);					
	}


	public void insertDefaultCategory(String blog_id) {
		sqlSession.insert(namespace + "insertDefaultCategory" , blog_id);
	}


	public List<CategoryVo> getCategory(String id) {
		return sqlSession.selectList(namespace + "getCategory", id);
	}


	public void deleteCategory(String id, String no) {
		Map<String, Object> map = new HashMap();
		map.put("id", id);
		map.put("no", no);
		sqlSession.selectList(namespace + "delete", map);
		
	}

}
