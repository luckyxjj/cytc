package com.ssm.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.ssm.pojo.Environment;
import com.ssm.service.IEnvironmentService;

@Controller
@RequestMapping("/environment")
public class EnvironmentController {

	@Resource
	private IEnvironmentService environmentService;

	// 获取当前时间
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String time = format.format(new Date());

	/* 获取所有工作环境信息 */
	@RequestMapping("/getEnvironment")
	public String getEnvironment(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			List<Environment> environmentList = environmentService.getAllEnvironment();
			if (environmentList.size() > 0) {
				model.addAttribute("environmentList", environmentList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "environment";
	}

	/* 获取工作环境信息 */
	@RequestMapping("/getEnvironmentInfo")
	public void getEnvironmentInfo(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		// 设置服务器端响应的编码格式
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			List<Environment> environmentList = environmentService.getAllEnvironment();
			if (environmentList.size() > 0) {
				response.getOutputStream().write(JSON.toJSONString(environmentList).getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* 通过id获取工作环境信息 */
	@RequestMapping("/getEnvironmentById")
	public String getEnvironmentById(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String id = request.getParameter("id");
			if (id == null && !"".equals(id)) {
				return "fail";
			}
			if (id != null && !"".equals(id)) {
				Environment environment = this.environmentService.getEnvironmentById(Integer.parseInt(id.trim()));
				model.addAttribute("environment", environment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "update_environment";
	}

	/* 添加工作环境信息 */
	@RequestMapping("/addEnvironment")
	public String addEnvironment(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session, @RequestParam("environmentFile") MultipartFile[] environmentFile) {
		response.setCharacterEncoding("utf-8");
		try {

			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */

			if (environmentFile.length > 0) {
				for (int i = 0; i < environmentFile.length; i++) {
					if (!environmentFile[i].isEmpty()) {
						String path = "upload/environment/" + environmentFile[i].getOriginalFilename();
						String filePath = request.getSession().getServletContext().getRealPath("/") + path;
						File saveDir = new File(filePath);
						if (!saveDir.getParentFile().exists()) {
							saveDir.getParentFile().mkdirs();
						}
						// 转存图片
						environmentFile[i].transferTo(saveDir);

						Environment environment = new Environment();
						environment.setImgUrl(path);
						environment.setCreateTime(time);
						int code = this.environmentService.addEnvironment(environment);
						if (code > 0) {
							model.addAttribute("tip", "添加成功");
						} else {
							model.addAttribute("tip", "添加失败");
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "add_environment";
	}

	/* 修改工作环境信息 */
	@RequestMapping("/updateEnvironment")
	public String updateEnvironment(HttpServletRequest request, Model model, HttpServletResponse response,
			@RequestParam("environmentFile") MultipartFile environmentFile, @RequestParam("imgId") String imgId,
			@RequestParam("imgUrl") String imgUrl) {
		Environment environment = new Environment();
		try {
			response.setCharacterEncoding("utf-8");
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */

			if (!environmentFile.isEmpty()) {
				String path = "upload/about/" + environmentFile.getOriginalFilename();
				String filePath = request.getSession().getServletContext().getRealPath("/") + path;
				File saveDir = new File(filePath);
				if (!saveDir.getParentFile().exists()) {
					saveDir.getParentFile().mkdirs();
				}
				// 转存图片
				environmentFile.transferTo(saveDir);
				environment.setImgUrl(path);
			}
			environment.setId(Integer.parseInt(imgId));
			environment.setUpdateTime(time);
			int code = this.environmentService.updateEnvironment(environment);
			if (code > 0) {
				model.addAttribute("tip", "修改成功");
				if (environment.getImgUrl() == null || "".equals(environment.getImgUrl())) {
					environment.setImgUrl(imgUrl);
				}

			} else {
				model.addAttribute("tip", "修改失败");
				environment.setImgUrl(imgUrl);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("environment", environment);
		return "update_environment";
	}

	/* 删除工作环境信息 */
	@RequestMapping("/deleteEnvironment")
	public void deleteEnvironment(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			session.setAttribute("login", "a");
			if (session.getAttribute("login") == null) {

				response.getOutputStream().write("请先登入".toString().getBytes("utf-8"));

			} else {
				String id = request.getParameter("id");
				if (id != null && !"".equals(id)) {
					int code = this.environmentService.deleteEnvironment(Integer.parseInt(id.trim()));
					if (code > 0) {

						response.getOutputStream().write("删除失败".getBytes("utf-8"));

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