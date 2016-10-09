package com.ssm.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.ssm.pojo.User;
import com.ssm.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private IUserService userService;
	// 获取当前时间
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String time = format.format(new Date());

	/* 获取所有用户信息 */
	@RequestMapping("/getUser")
	public String getUser(HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			List<User> usertList = userService.getAllUser();
			if (usertList.size() > 0) {
				model.addAttribute("UsertList", usertList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "user";
	}

	/* 获取所有用户信息 */
	@RequestMapping("/getUserInfo")
	public void getUserInfo(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		// 设置服务器端响应的编码格式
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			List<User> usertList = userService.getAllUser();

			if (usertList.size() > 0) {
				response.getOutputStream().write(JSON.toJSONString(usertList).getBytes("utf-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* 通过id获取用户信息 */
	@RequestMapping("/getUserById")
	public String getUserById(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String id = request.getParameter("id").trim();
			if (id != null && !"".equals(id)) {
				User user = this.userService.getUserById(Integer.parseInt(id.trim()));
				if (user != null) {
					model.addAttribute("user", user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "update_user";

	}

	/* 添加用户信息 */
	@RequestMapping("/addUser")
	public String addUser(HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session) {
		response.setCharacterEncoding("utf-8");
		/*
		 * if (session.getAttribute("login") == null) { return "login"; }
		 */
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setCreateTime(time);
		int code = this.userService.addUser(user);
		if (code > 0) {

			model.addAttribute("tip", "添加成功");
		} else {
			model.addAttribute("tip", "添加失败");
		}
		return "add_user";
	}

	/* 修改用户信息 */
	@RequestMapping("/updateUser")
	public String updateUser(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		if (session.getAttribute("login") == null) {
			return "login";
		}
		String id = request.getParameter("id");
		if (id == null && !"".equals(id)) {
			return "fail";
		}
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		User user = new User();
		if (id != null && !"".equals(id)) {
			user.setId(Integer.parseInt(id.trim()));
			user.setUserName(userName);
			user.setPassword(password);
			user.setUpdateTime(time);
			int code = this.userService.updateUser(user);
			if (code > 0) {

				model.addAttribute("tip", "修改成功");
			} else {
				model.addAttribute("tip", "修改失败");
			}
		}

		model.addAttribute("user", user);
		return "update_user";
	}

	/* 删除用户信息 */
	@RequestMapping("/deleteUser")
	public void deleteUser(HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			session.setAttribute("login", "a");
			if (session.getAttribute("login") == null) {

				response.getOutputStream().write("请先登入".toString().getBytes("utf-8"));

			} else {
				String id = request.getParameter("id");
				if (id != null && !"".equals(id)) {
					int code = this.userService.deleteUser(Integer.parseInt(id.trim()));
					if (code > 0) {
						response.getOutputStream().write("删除成功".getBytes("utf-8"));
					} else {
						response.getOutputStream().write("删除失败".getBytes("utf-8"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}