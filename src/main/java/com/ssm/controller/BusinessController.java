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
import com.ssm.pojo.Business;
import com.ssm.pojo.BusinessImg;
import com.ssm.service.IBusinessImgService;
import com.ssm.service.IBusinessService;

@Controller
@RequestMapping("/business")
public class BusinessController {

	@Resource
	private IBusinessService businessService;

	@Resource
	private IBusinessImgService businessImgService;

	// 获取当前时间
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String time = format.format(new Date());

	/* 获取所有业务表信息 */
	@RequestMapping("/getBusiness")
	public String getBusiness(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			List<Business> businessList = this.businessService.getAllBusiness();
			if (businessList.size() > 0) {
				for (Business business : businessList) {
					List<BusinessImg> businessImgList = this.businessImgService.getByBusinessId(business.getId());
					business.setBusinessImgList(businessImgList);
				}
				model.addAttribute("businessList", businessList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "business";
	}

	/* 获取业务表信息 */
	@RequestMapping("/getBusinessInfo")
	public void getBusinessInfo(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		// 设置服务器端响应的编码格式
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			List<Business> businessList = this.businessService.getAllBusiness();
			if (businessList.size() > 0) {
				for (Business business : businessList) {
					List<BusinessImg> businessImgList = this.businessImgService.getByBusinessId(business.getId());
					business.setBusinessImgList(businessImgList);
				}
				response.getOutputStream().write(JSON.toJSONString(businessList).getBytes("utf-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* 通过id获取业务表信息 */
	@RequestMapping("/getBusinessById")
	public String getBusinessById(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String id = request.getParameter("id");
			if (id != null && !"".equals(id)) {
				Business business = this.businessService.getBusinessById(Integer.parseInt(id));
				List<BusinessImg> businessImgList = this.businessImgService.getByBusinessId(Integer.parseInt(id));
				business.setBusinessImgList(businessImgList);
				if (business != null) {
					model.addAttribute("business", business);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "update_business";
	}

	/* 添加业务表信息 */
	@RequestMapping("/addBusiness")
	public String addBusiness(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session, @RequestParam("businessFile") MultipartFile[] businessFile) {
		response.setCharacterEncoding("utf-8");
		try {

			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			if (businessFile.length > 0) {
				String description = request.getParameter("description");
				Business business = new Business();
				business.setDescription(description);
				business.setCreateTime(time);
				int id = this.businessService.addBusiness(business);
				if (id > 0) {
					for (MultipartFile multipartFile : businessFile) {
						if (!multipartFile.isEmpty()) {
							String path = "upload/business/" + multipartFile.getOriginalFilename();
							String filePath = request.getSession().getServletContext().getRealPath("/") + path;
							File saveDir = new File(filePath);
							if (!saveDir.getParentFile().exists()) {
								saveDir.getParentFile().mkdirs();
							}
							// 转存图片
							multipartFile.transferTo(saveDir);
							BusinessImg businessImg = new BusinessImg();
							businessImg.setBusinessId(id);
							businessImg.setImgUrl(path);
							businessImg.setCreateTiem(time);
							this.businessImgService.addBusinessImg(businessImg);
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

		return "add_business";
	}

	/* 修改业务表信息 */
	@RequestMapping("/updateBusiness")
	public String updateBusiness(HttpServletRequest request, Model model, HttpServletResponse response,
			@RequestParam("businessFile") MultipartFile[] businessFile, @RequestParam("imgId") String[] imgId,
			@RequestParam("imgUrl") String[] imgUrl) {
		Business business = new Business();
		List<BusinessImg> businessImgList = new ArrayList<BusinessImg>();
		try {
			response.setCharacterEncoding("utf-8");
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String id = request.getParameter("id");
			if (id == null && !"".equals(id)) {
				return "fail";
			}
			String description = request.getParameter("description");

			business.setId(Integer.parseInt(id.trim()));
			business.setDescription(description);
			business.setUpdateTime(time);
			int code = this.businessService.updateBusiness(business);
			if (code > 0) {
				if (businessFile.length > 0) {
					for (int i = 0; i < businessFile.length; i++) {
						if (!businessFile[i].isEmpty()) {
							String path = "upload/business/" + businessFile[i].getOriginalFilename();
							String filePath = request.getSession().getServletContext().getRealPath("/") + path;
							File saveDir = new File(filePath);
							if (!saveDir.getParentFile().exists()) {
								saveDir.getParentFile().mkdirs();
							}
							// 转存图片
							businessFile[i].transferTo(saveDir);
							BusinessImg businessImg = new BusinessImg();
							businessImg.setId(Integer.parseInt(imgId[i]));
							businessImg.setImgUrl(path);
							businessImg.setUpdateTime(time);
							int code2 = this.businessImgService.updateBusinessImg(businessImg);
							if (code2 > 0) {
								businessImgList.add(businessImg);
							} else {
								BusinessImg businessImg2 = new BusinessImg();
								businessImg2.setId(Integer.parseInt(imgId[i]));
								businessImg2.setImgUrl(imgUrl[i]);
								businessImgList.add(businessImg2);
							}

						} else {

							BusinessImg businessImg2 = new BusinessImg();
							businessImg2.setId(Integer.parseInt(imgId[i]));
							businessImg2.setImgUrl(imgUrl[i]);
							businessImgList.add(businessImg2);
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
		business.setBusinessImgList(businessImgList);
		model.addAttribute("business", business);
		return "update_business";
	}

	/* 删除业务表信息 */
	@RequestMapping("/deleteBusiness")
	public void deleteBusiness(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			session.setAttribute("login", "a");
			if (session.getAttribute("login") == null) {

				response.getOutputStream().write("请先登入".toString().getBytes("utf-8"));

			} else {
				String id = request.getParameter("id");
				if (id != null && !"".equals(id)) {
					int imgCode = this.businessImgService.deleteByBusinessId(Integer.parseInt(id));
					if (imgCode > 0) {
						int code = this.businessService.deleteBusiness(Integer.parseInt(id.trim()));
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