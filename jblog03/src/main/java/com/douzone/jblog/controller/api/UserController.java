package com.douzone.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@RestController("userControllerApi") // 이거안하면 이미있는 UserController랑 중복되기때문에 DispatcherServlet -> Servlet.init()이 에러띄움 그래서 이름 직접 정해줌
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@GetMapping("/checkid")
	public Object checkId(@RequestParam(value="id", required=true, defaultValue="") String id) {
		UserVo vo = userService.getUser(id);
		return JsonResult.success(vo != null);
	}
}

