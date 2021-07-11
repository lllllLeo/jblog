package com.douzone.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}") // assets 제외한 문자는 받아라 : ? 있어도 되고 없어도 되고 , ! 제외 
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private FileUploadService fileUploadService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	@Autowired
	private ServletContext application;
	
	
//	메인페이지오면 default category랑 맨 최근꺼 보여줘야함
	@RequestMapping({ "", "/{pathNo1}", "{pathNo1}/{pathNo2}" })
	public String index(
			@PathVariable("id") String id, 
			@PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2,
			Model model
			) {
		Long categoryNo = 0L;
		Long postNo = 0L;

		if (pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		System.out.println("id:" + id);
		System.out.println("category:" + categoryNo);
		System.out.println("post:" + postNo);
		Map<String, Object> map = new HashMap();
		map = blogService.getBlogMain(id, categoryNo, postNo);
		application.setAttribute("title", blogService.getBlog(id).getTitle());
		model.addAttribute("map", map);
		return "blog/main";
	}
	
	@Auth
//	@ModelAttribute BlogVo vo
	@GetMapping("admin/basic") //      /{id}/admin/basic
	public String adminBasic(@PathVariable("id") String id, Model model) {
		BlogVo blogVo= blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin/basic";
	}
	@Auth
	@PostMapping("admin/basic") //      /{id}/admin/basic
	public String adminBasic(@PathVariable("id") String id,
			BlogVo blogVo,
			@RequestParam("file") MultipartFile file) {
		String url = fileUploadService.restore(file);
		blogVo.setLogo(url);
		blogService.updateBlog(blogVo);
		return "redirect:/"+id;
	}
	@Auth
	@GetMapping("admin/category") //      /{id}/admin/category
	public String adminCategory(@PathVariable("id") String id, Model model) {
		List<CategoryVo> list = categoryService.getCategory(id);
		System.out.println(list);
		model.addAttribute("list", list);
		return "blog/admin/category";
	}
	
	@Auth
	@PostMapping("admin/category") //      /{id}/admin/category
	public String adminAddCategory(@PathVariable("id") String id, CategoryVo categoryVo) {
		categoryVo.setBlog_id(id);
		categoryService.insertCategory(categoryVo);
		return "redirect:/"+id+"/admin/category";
	}
	
	@Auth
	@GetMapping("admin/category/delete/{no}") //      /{id}/admin/category
	public String adminDeleteCategory(@PathVariable("id") String id, @PathVariable("no") String no) { 
		categoryService.deleteCategory(id, no);
		return "redirect:/"+id+"/admin/category";
	}
	
	@Auth
	@GetMapping("admin/write") //      /{id}/admin/category
	public String adminWrite(@PathVariable("id") String id, Model model) {
		List<CategoryVo> list = categoryService.getCategory(id);
		model.addAttribute("list", list);
		return "blog/admin/write";
	}
	
	@Auth
	@PostMapping("admin/write") //      /{id}/admin/category
	public String adminWrite(@PathVariable("id") String id, @RequestParam("category") Long no, PostVo postVo) {
		postVo.setCategory_no(no);
		postService.insertPost(postVo);
//		javascript 로 status해서 alert띄우기?
		return "redirect:/"+id+"/admin/write";
	}
}
