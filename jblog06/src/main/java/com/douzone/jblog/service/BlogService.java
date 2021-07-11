package com.douzone.jblog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {
	
	
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private PostRepository postRepository;
	

	public void insertBlog(String id) {
	   BlogVo vo = new BlogVo();
	   vo.setId(id);
	   vo.setTitle(id + " 's Blog");
	   blogRepository.insertBlog(vo);	
	}


	public Map<String, Object> getBlogMain(String id, Long categoryNo, Long postNo) {
		Map<String, Object> map = new HashMap<>();
		List<CategoryVo> categoryList = new ArrayList<CategoryVo>();
		List<PostVo> postList = new ArrayList<PostVo>();
		 
		BlogVo blogVo = blogRepository.getBlog(id);
		categoryList = categoryRepository.getCategory(id);
		postList = postRepository.getPostList(id,0L);
		PostVo postVo = postRepository.getPostOne(id, categoryNo, postNo);
		
		System.out.println("postList =1= " + postList);
		
		////		경로에 카테고리, 글 번호 다 들어왔을때
		if (postNo != 0L && categoryNo != 0L) {
			System.out.println("if문 ");
////		categoryNo = pathNo1.get(); // 이름 맨위 카테고리 뽑기
//			categoryResult = categoryRepository.isPresent(categoryNo);
//			postNo = pathNo2.get();		// categoryNo의 최근 글 뽑기
//			postList = postRepository.getPostOne(id, categoryNo, postNo);
			postList = postRepository.getPostList(id, categoryNo);
			postVo = postRepository.getPostOne(id, categoryNo, postNo);
		} else if (categoryNo != 0L){
//			categoryNo = pathNo1.get(); // 이름 맨위 카테고리 뽑기
			System.out.println("else if문 ");
			postList = postRepository.getPostList(id, categoryNo);
			postVo = postRepository.getPostOne(id, categoryNo, postNo);
//			for (CategoryVo categoryVo : categoryList) {
//				if(categoryNo == categoryVo.getNo()) {
//					postList = postRepository.getPost(id, categoryNo);
//					System.out.println("postList =2= " + postList);
//					break;
//				}
//				System.out.println("for문");
//			}
			
			
//			없음 categotyNo == 0
			
		}
			
		System.out.println("postList =3= " + postList);
		map.put("blogVo", blogVo);
		map.put("categoryList", categoryList);
		map.put("postList", postList);
		map.put("postVo", postVo);

		return map;
	}


	public BlogVo getBlog(String id) {
		return blogRepository.getBlog(id);
	}


	public void updateBlog(BlogVo blogVo) {
		blogRepository.updateBlog(blogVo);
	}
}
