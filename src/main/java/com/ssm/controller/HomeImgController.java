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
import com.ssm.pojo.HomeImg;
import com.ssm.service.IHomeImgService;

@Controller
@RequestMapping("/homeImg")
public class HomeImgController {

	@Resource
	private IHomeImgService homeImgService;

	// 获取当前时间
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String time = format.format(new Date());

	/* 获取所有工作环境信息 */
	@RequestMapping("/getHomeImg")
	public String getHomeImg(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			List<HomeImg> homeImgList = this.homeImgService.getAllHomeImg();
			if (homeImgList.size() > 0) {
				model.addAttribute("homeImgList", homeImgList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "homeimg";
	}

	/* 获取主页轮播图片 */
	@RequestMapping("/getHomeImgInfo")
	public void getHomeImgInfo(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		// 设置服务器端响应的编码格式
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			List<HomeImg> homeImgList = this.homeImgService.getAllHomeImg();
			if (homeImgList.size() > 0) {
				response.getOutputStream().write(JSON.toJSONString(homeImgList).getBytes("utf-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* 通过id获取主页轮播图片 */
	@RequestMapping("/getHomeImgById")
	public String getHomeImgById(HttpServletRequest request, Model model, HttpServletResponse response,
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
				HomeImg homeImg = this.homeImgService.getHomeImgById(Integer.parseInt(id.trim()));
				model.addAttribute("homeImg", homeImg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "update_homeimg";
	}

	/* 添加主页轮播图片 */
	@RequestMapping("/addHomeImg")
	public String addHomeImg(HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session,
			@RequestParam("homeImgFile") MultipartFile[] homeImgFile) {
		response.setCharacterEncoding("utf-8");
		try {

			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */

			if (homeImgFile.length > 0) {
				for (int i = 0; i < homeImgFile.length; i++) {
					if (!homeImgFile[i].isEmpty()) {
						String path = "upload/homeImg/" + homeImgFile[i].getOriginalFilename();
						String filePath = request.getSession().getServletContext().getRealPath("/") + path;
						File saveDir = new File(filePath);
						if (!saveDir.getParentFile().exists()) {
							saveDir.getParentFile().mkdirs();
						}
						// 转存图片
						homeImgFile[i].transferTo(saveDir);

						HomeImg homeImg = new HomeImg();
						homeImg.setImgUrl(path);
						homeImg.setCreateTime(time);
						int code = this.homeImgService.addHomeImg(homeImg);
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

		return "add_homeimg";
	}

	/* 修改主页轮播图片 */
	@RequestMapping("/updateHomeImg")
	public String updateHomeImg(HttpServletRequest request, Model model, HttpServletResponse response,
			@RequestParam("homeImgFile") MultipartFile homeImgFile, @RequestParam("imgUrl") String imgUrl) {
		HomeImg homeImg = new HomeImg();
		try {
			response.setCharacterEncoding("utf-8");
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String id = request.getParameter("id");
			if (id == null && !"".equals(id)) {
				return "fail";
			}

			if (!homeImgFile.isEmpty()) {
				String path = "upload/homeImg/" + homeImgFile.getOriginalFilename();
				String filePath = request.getSession().getServletContext().getRealPath("/") + path;
				File saveDir = new File(filePath);
				if (!saveDir.getParentFile().exists()) {
					saveDir.getParentFile().mkdirs();
				}
				// 转存图片
				homeImgFile.transferTo(saveDir);
				homeImg.setImgUrl(path);

			}
			homeImg.setId(Integer.parseInt(id));
			homeImg.setUpdateTime(time);
			int code = this.homeImgService.updateHomeImg(homeImg);
			if (code > 0) {
				if (homeImg.getImgUrl() == null || "".equals(homeImg.getImgUrl())) {
					homeImg.setImgUrl(imgUrl);
				}
				model.addAttribute("tip", "修改成功");
			} else {
				homeImg.setImgUrl(imgUrl);
				model.addAttribute("tip", "修改失败");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("homeImg", homeImg);
		return "update_homeimg";
	}

	/* 删除主页轮播图片 */
	@RequestMapping("/deleteHomeImg")
	public void deleteHomeImg(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			session.setAttribute("login", "a");
			if (session.getAttribute("login") == null) {

				response.getOutputStream().write("请先登入".toString().getBytes("utf-8"));

			} else {
				String id = request.getParameter("id");
				if (id != null && !"".equals(id)) {
					int code = this.homeImgService.deleteHomeImg(Integer.parseInt(id.trim()));
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