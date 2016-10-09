package com.ssm.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ssm.pojo.About;
import com.ssm.pojo.AboutImg;
import com.ssm.service.IAboutImgService;
import com.ssm.service.IAboutService;

@Controller
@RequestMapping("/about")
public class AboutController {

	@Resource
	private IAboutService aboutService;

	@Resource
	private IAboutImgService aboutImgService;

	// 获取当前时间
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String time = format.format(new Date());

	/* 获取所有关于信息jj */
	@RequestMapping("/getAbout")
	public String getAbout(HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			List<About> aboutList = this.aboutService.getAllAbout();
			if (aboutList.size() > 0) {
				for (About about : aboutList) {
					List<AboutImg> aboutImgList = this.aboutImgService.getByAboutId(about.getId());
					about.setAboutImgList(aboutImgList);
				}
				model.addAttribute("aboutList", aboutList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "about";
	}

	/* 获取关于信息 */
	@RequestMapping("/getAboutInfo")
	public void getAboutInfo(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		// 设置服务器端响应的编码格式
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			List<About> aboutList = this.aboutService.getAllAbout();

			if (aboutList.size() > 0) {
				for (About about : aboutList) {
					List<AboutImg> aboutImgList = this.aboutImgService.getByAboutId(about.getId());
					about.setAboutImgList(aboutImgList);
				}
				response.getOutputStream().write(JSON.toJSONString(aboutList).getBytes("utf-8"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* 通过id获取关于信息 */
	@RequestMapping("/getAboutById")
	public String getAboutById(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String id = request.getParameter("id");
			if (id != null && !"".equals(id)) {
				About about = this.aboutService.getAboutById(Integer.parseInt(id.trim()));
				List<AboutImg> aboutImgList = this.aboutImgService.getByAboutId(Integer.parseInt(id));
				about.setAboutImgList(aboutImgList);
				if (about != null) {
					model.addAttribute("about", about);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "update_about";
	}

	/* 添加关于信息 */
	@RequestMapping("/addAbout")
	public String addAbout(HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session,
			@RequestParam("aboutFile") MultipartFile[] aboutFile) {
		response.setCharacterEncoding("utf-8");
		try {

			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			if (aboutFile.length > 0) {
				String introduce = request.getParameter("introduce");
				String culture = request.getParameter("culture");

				About about = new About();
				about.setIntroduce(introduce);
				about.setCulture(culture);
				about.setCreateTime(time);
				int id = this.aboutService.addAbout(about);
				if (id > 0) {// 添加关于图片
					for (MultipartFile multipartFile : aboutFile) {
						if (!multipartFile.isEmpty()) {
							String path = "upload/about/" + multipartFile.getOriginalFilename();
							String filePath = request.getSession().getServletContext().getRealPath("/") + path;
							File saveDir = new File(filePath);
							if (!saveDir.getParentFile().exists()) {
								saveDir.getParentFile().mkdirs();
							}
							// 转存图片
							multipartFile.transferTo(saveDir);
							AboutImg aboutImg = new AboutImg();
							aboutImg.setAboutId(about.getId());
							aboutImg.setImgUrl(path);
							aboutImg.setCreateTime(time);
							this.aboutImgService.addAboutImg(aboutImg);
						}

					}

					model.addAttribute("tip", "添加成功");
				} else {
					model.addAttribute("tip", "添加失败");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "add_about";
	}

	/* 修改关于信息 */
	@RequestMapping("/updateAbout")
	public String updateAbout(HttpServletRequest request, Model model, HttpServletResponse response,
			@RequestParam("aboutFile") MultipartFile[] aboutFile, @RequestParam("imgId") String[] imgId,
			@RequestParam("imgUrl") String[] imgUrl) {
		About about = new About();
		List<AboutImg> aboutImgList = new ArrayList<AboutImg>();
		try {
			response.setCharacterEncoding("utf-8");
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String id = request.getParameter("id");
			if (id == null && !"".equals(id)) {
				return "fail";
			}
			String introduce = request.getParameter("introduce");
			String culture = request.getParameter("culture");

			about.setId(Integer.parseInt(id));
			about.setIntroduce(introduce);
			about.setCulture(culture);
			about.setUpdateTime(time);
			int code = this.aboutService.updateAbout(about);
			if (code > 0) {
				if (aboutFile.length > 0) {// 修改关于图片
					for (int i = 0; i < aboutFile.length; i++) {
						if (!aboutFile[i].isEmpty()) {
							String path = "upload/about/" + aboutFile[i].getOriginalFilename();
							String filePath = request.getSession().getServletContext().getRealPath("/") + path;
							File saveDir = new File(filePath);
							if (!saveDir.getParentFile().exists()) {
								saveDir.getParentFile().mkdirs();
							}
							// 转存图片
							aboutFile[i].transferTo(saveDir);
							AboutImg aboutImg = new AboutImg();
							aboutImg.setId(Integer.parseInt(imgId[i]));
							aboutImg.setImgUrl(path);
							aboutImg.setUpdateTime(time);
							int code2 = this.aboutImgService.updateAboutImg(aboutImg);
							if (code2 > 0) {
								aboutImgList.add(aboutImg);
							} else {
								AboutImg aboutImg2 = new AboutImg();
								aboutImg2.setId(Integer.parseInt(imgId[i]));
								aboutImg2.setImgUrl(imgUrl[i]);
								aboutImgList.add(aboutImg2);
							}

						} else {

							AboutImg aboutImg = new AboutImg();
							aboutImg.setId(Integer.parseInt(imgId[i]));
							aboutImg.setImgUrl(imgUrl[i]);
							aboutImgList.add(aboutImg);
						}
					}

				}
				model.addAttribute("tip", "修改成功");
			} else {
				model.addAttribute("tip", "修改失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		about.setAboutImgList(aboutImgList);
		model.addAttribute("about", about);
		return "update_about";
	}

	/* 删除关于信息 */
	@RequestMapping("/deleteAbout")
	public void deleteAbout(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			session.setAttribute("login", "a");
			if (session.getAttribute("login") == null) {

				response.getOutputStream().write("请先登入".toString().getBytes("utf-8"));

			} else {
				String id = request.getParameter("id");
				if (id != null && !"".equals(id)) {
					// 先删除关于图表中的数据
					int imgCode = this.aboutImgService.deleteByAboutId(Integer.parseInt(id));
					if (imgCode > 0) {
						int code = this.aboutService.deleteAbout(Integer.parseInt(id.trim()));
						if (code > 0) {
							response.getOutputStream().write("删除成功".getBytes("utf-8"));
						} else {
							response.getOutputStream().write("删除失败".getBytes("utf-8"));
						}
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