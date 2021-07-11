package com.douzone.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.PassThroughSourceExtractor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.PascalCaseStrategy;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("loginInterceptor");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		UserVo authUser = userService.getUser(id, password);   
		
		if(authUser == null) {
			System.out.println("로그인 실패 in LoginInterceptor");
			request.setAttribute("id", id);
			request.setAttribute("result", "fail");
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
			return false;
		}
		
//		세션 처리
		System.out.println("로그인 성공 in LoginInterceptor authUser = " + authUser);
		System.out.println(authUser);
		HttpSession session =  request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath());
		return false;
	}

}
