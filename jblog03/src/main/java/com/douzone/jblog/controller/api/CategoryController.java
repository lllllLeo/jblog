package com.douzone.jblog.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.vo.CategoryVo;


@RestController("categoryControllerApi")
@RequestMapping("/{id:(?!assets).*}/admin/category/api")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
//	@Auth
	@GetMapping("")
	public JsonResult list(@PathVariable("id") String id) {
		System.out.println(id + "  -  controller list");
		List<CategoryVo> list = categoryService.getCategory(id);
		System.out.println(list);
		return JsonResult.success(list);
	}
	
//	@Auth
	@PostMapping("/add") //      /{id}/admin/category
	public JsonResult add(@PathVariable("id") String id, @RequestBody CategoryVo vo) {
		System.out.println("add   " + vo);
		vo.setBlog_id(id);
		categoryService.insertCategory(vo);
		System.out.println(vo);
		return JsonResult.success(vo);
	}
	
//	@Auth
	@PostMapping("/delete/{no}") //      /{id}/admin/category
	public JsonResult delete(@PathVariable("id") String id, @PathVariable("no") String no) { 
		categoryService.deleteCategory(id, no);
		return JsonResult.success(no);
	}
}
