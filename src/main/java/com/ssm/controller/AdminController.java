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

import com.ssm.pojo.Admin;
import com.ssm.service.IAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Resource
	private IAdminService adminService;
	// 获取当前时间
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String time = format.format(new Date());

	/* 获取所有管理员信息 */
	@RequestMapping("/getAdmin")
	public String getAdmin(HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			List<Admin> admintList = adminService.getAllAdmin();
			if (admintList.size() > 0) {
				model.addAttribute("admintList", admintList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "admin";
	}

	/* 通过id获取管理员信息 */
	@RequestMapping("/getAdminById")
	public String getAdminById(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String id = request.getParameter("id").trim();
			if (id != null && !"".equals(id)) {
				Admin admin = this.adminService.getAdminById(Integer.parseInt(id.trim()));

				if (admin != null) {
					model.addAttribute("admin", admin);

					/*
					 * JSONArray jsonArray = new JSONArray(); JSONObject object
					 * = new JSONObject(); object.put("userName",
					 * admin.getUserName()); object.put("password",
					 * admin.getPassword());
					 * 
					 * jsonArray.add(object);
					 */

					/*
					 * response.getOutputStream().write(jsonArray.toString().
					 * getBytes());
					 */
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "update_admin";
	}

	/* 添加管理员信息 */
	@RequestMapping("/addAdmin")
	public String addAdmin(HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session) {
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
			admin.setCreateTime(time);
			int code = this.adminService.addAdmin(admin);
			if (code > 0) {

				model.addAttribute("tip", "添加成功");
			} else {
				model.addAttribute("tip", "添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "add_admin";
	}

	/* 修改管理员信息 */
	@RequestMapping("/updateAdmin")
	public String updateAdmin(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		try {
			response.setCharacterEncoding("utf-8");
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String id = request.getParameter("id");
			if (id == null && !"".equals(id)) {
				return "fail";
			}
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			Admin admin = new Admin();
			if (id != null && !"".equals(id)) {
				admin.setId(Integer.parseInt(id));
				admin.setUserName(userName);
				admin.setPassword(password);
				admin.setUpdateTime(time);
				int code = this.adminService.updateAdmin(admin);
				if (code > 0) {

					model.addAttribute("tip", "修改成功");
				} else {
					model.addAttribute("tip", "修改失败");
				}
				model.addAttribute("admin", admin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "update_admin";
	}

	/* 删除管理员信息 */
	@RequestMapping("/deleteAdmin")
	public void deleteAdmin(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			if (session.getAttribute("login") == null) {

				response.getOutputStream().write("请先登入".toString().getBytes("utf-8"));

			} else {
				String id = request.getParameter("id");
				if (id != null && !"".equals(id)) {
					int code = this.adminService.deleteAdmin(Integer.parseInt(id.trim()));
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