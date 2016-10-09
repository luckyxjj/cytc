package com.ssm.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm.pojo.Admin;
import com.ssm.pojo.User;
import com.ssm.service.IAdminService;
import com.ssm.service.IUserService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Resource
	private IAdminService adminService;

	@Resource
	private IUserService userService;
	// 获取当前时间
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String time = format.format(new Date());

	/* 管理员登入 */
	@RequestMapping("/adminLoginValidate")
	public String adminLoginValidate(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			Admin admin = new Admin();
			admin.setUserName(userName);
			admin.setPassword(password);
			int id = this.adminService.loginValidate(admin);
			if (id > 0) {
				session.invalidate();
				HttpSession session2 = request.getSession();
				session2.setAttribute("login", id);
				session2.setMaxInactiveInterval(3600);
				return "company";
			} else {
				model.addAttribute("tip", "用户名或密码错误");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "login";
	}

	/* 用户登入 */
	@RequestMapping("/userLoginValidate")
	public void userLoginValidate(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			User user = new User();
			user.setUserName(userName);
			user.setPassword(password);
			int id = this.userService.loginValidate(user);
			if (id > 0) {
				session.invalidate();
				HttpSession session2 = request.getSession();
				session2.setAttribute("userLogin", id);
				session2.setMaxInactiveInterval(3600);
				response.getOutputStream().write("登入成功".getBytes("utf-8"));
			} else {
				response.getOutputStream().write("用户名或密码错误，请重新输入！".getBytes("utf-8"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		/* return "admin_back"; */
	}

}