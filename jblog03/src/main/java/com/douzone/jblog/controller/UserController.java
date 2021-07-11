package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/join")
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}
	
	@PostMapping("/join")
	public String join(@ModelAttribute UserVo vo,  Model model) {
		System.out.println("join "+vo);
//		if(result.hasErrors()) {
////			List<ObjectError> list = result.getAllErrors();
////			for(ObjectError error : list) {
////				System.out.println(error);
////			}
//			model.addAllAttributes(result.getModel());
//			return "user/join";
//		}
		userService.insertUser(vo);
		blogService.insertBlog(vo.getId());
		categoryService.insertDefaultCategory(vo.getId());
		
		
		return "redirect:/user/joinsuccess";
	}
	
	@GetMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
}
