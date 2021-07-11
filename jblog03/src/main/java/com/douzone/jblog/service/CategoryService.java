package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void insertCategory(CategoryVo categoryVo) {	
		categoryRepository.insertCategory(categoryVo);			
	}

	public void insertDefaultCategory(String blog_id) {
		categoryRepository.insertDefaultCategory(blog_id);
	}

	public List<CategoryVo> getCategory(String id) {
		return categoryRepository.getCategory(id);
	}

	public void deleteCategory(String id, String no) {
		categoryRepository.deleteCategory(id, no);
		
	}

}
