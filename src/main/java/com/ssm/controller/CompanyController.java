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
import com.ssm.pojo.Company;
import com.ssm.pojo.CompanyImg;
import com.ssm.service.ICompanyImgService;
import com.ssm.service.ICompanyService;

@Controller
@RequestMapping("/company")
public class CompanyController {

	@Resource
	private ICompanyService companyService;

	@Resource
	private ICompanyImgService companyImgService;

	// 获取当前时间
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String time = format.format(new Date());

	/* 获取所有公司信息 */
	@RequestMapping("/getCompany")
	public String getCompany(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			List<Company> companyList = this.companyService.getAllCompany();
			if (companyList.size() > 0) {
				for (Company company : companyList) {
					List<CompanyImg> companyImgList = this.companyImgService.getByCompanyId(company.getId());
					company.setCompanyImgList(companyImgList);
				}
				model.addAttribute("companyList", companyList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "company";
	}

	/* 获取公司信息 */
	@RequestMapping("/getCompanyInfo")
	public void getCompanyInfo(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		// 设置服务器端响应的编码格式
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			List<Company> companieList = this.companyService.getAllCompany();
			if (companieList.size() > 0) {
				for (Company company : companieList) {
					List<CompanyImg> companyImgList = this.companyImgService.getByCompanyId(company.getId());
					company.setCompanyImgList(companyImgList);
				}
				response.getOutputStream().write(JSON.toJSONString(companieList).getBytes("utf-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* 通过id获取公司信息 */
	@RequestMapping("/getCompanyById")
	public String getCompanyById(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String id = request.getParameter("id");
			if (id != null && !"".equals(id)) {
				Company company = this.companyService.getCompanyById(Integer.parseInt(id.trim()));
				if (company != null) {
					List<CompanyImg> companyImgList = this.companyImgService.getByCompanyId(Integer.parseInt(id));
					company.setCompanyImgList(companyImgList);
					model.addAttribute("company", company);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "update_company";
	}

	/* 添加公司信息 */
	@RequestMapping("/addCompany")
	public String addCompany(HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session,
			@RequestParam("logoFile") MultipartFile logoFile,
			@RequestParam("imagePhotoFile") MultipartFile imagePhotoFile,
			@RequestParam("mapFile") MultipartFile[] mapFile) {
		response.setCharacterEncoding("utf-8");
		try {

			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			if (!logoFile.isEmpty() && !imagePhotoFile.isEmpty()) {
				String path1 = "upload/company/" + logoFile.getOriginalFilename();
				String filePath1 = request.getSession().getServletContext().getRealPath("/") + path1;
				File saveDir1 = new File(filePath1);
				if (!saveDir1.getParentFile().exists()) {
					saveDir1.getParentFile().mkdirs();
				}
				// 转存logo图片
				logoFile.transferTo(saveDir1);

				String path2 = "upload/company/" + imagePhotoFile.getOriginalFilename();
				String filePath2 = request.getSession().getServletContext().getRealPath("/") + path2;
				File saveDir2 = new File(filePath2);
				if (!saveDir2.getParentFile().exists()) {
					saveDir2.getParentFile().mkdirs();
				}
				// 转存公司形象照片
				imagePhotoFile.transferTo(saveDir2);

				String companyName = request.getParameter("companyName");
				String phone = request.getParameter("phone");
				String fax = request.getParameter("fax");
				String address = request.getParameter("address");
				String qq = request.getParameter("qq");
				String mail = request.getParameter("mail");
				String microblog = request.getParameter("microblog");
				String wechat = request.getParameter("wechat");

				Company company = new Company();
				company.setCompanyName(companyName);
				company.setPhone(phone);
				company.setFax(fax);
				company.setAddress(address);
				company.setQq(qq);
				company.setMail(mail);
				company.setMicroblog(microblog);
				company.setWechat(wechat);
				company.setLogo(path1);
				company.setImagePhoto(path2);
				company.setCreateTime(time);
				int code = this.companyService.addCompany(company);
				if (code > 0) {
					if (mapFile.length > 0) {

						for (int i = 0; i < mapFile.length; i++) {
							if (!mapFile[i].isEmpty()) {
								String path3 = "upload/company/" + mapFile[i].getOriginalFilename();
								String filePath3 = request.getSession().getServletContext().getRealPath("/") + path3;
								File saveDir3 = new File(filePath3);
								if (!saveDir3.getParentFile().exists()) {
									saveDir3.getParentFile().mkdirs();
								}
								// 转存地图
								mapFile[i].transferTo(saveDir3);

								CompanyImg companyImg = new CompanyImg();
								companyImg.setCompanyId(company.getId());
								companyImg.setImgUrl(path3);
								companyImg.setCreateTime(time);
								this.companyImgService.addCompanyImg(companyImg);
							}

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

		return "add_company";
	}

	/* 修改公司信息 */
	@RequestMapping("/updateCompany")
	public String updateCompany(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session, @RequestParam("logoFile") MultipartFile logoFile,
			@RequestParam("imagePhotoFile") MultipartFile imagePhotoFile,
			@RequestParam("mapFile") MultipartFile[] mapFile, @RequestParam("mapId") String[] mapId,
			@RequestParam("logoUrl") String logoUrl, @RequestParam("imagePhotoUrl") String imagePhotoUrl,
			@RequestParam("mapUrl") String[] mapUrl) {
		Company company = new Company();
		List<CompanyImg> companyImgList = new ArrayList<CompanyImg>();
		try {
			response.setCharacterEncoding("utf-8");
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */

			if (!logoFile.isEmpty()) {
				String path1 = "upload/company/" + logoFile.getOriginalFilename();
				String filePath1 = request.getSession().getServletContext().getRealPath("/") + path1;
				File saveDir1 = new File(filePath1);
				if (!saveDir1.getParentFile().exists()) {
					saveDir1.getParentFile().mkdirs();
				}
				// 转存logo图片
				logoFile.transferTo(saveDir1);
				company.setLogo(path1);
			} else {
				company.setLogo(logoUrl);
			}
			if (!imagePhotoFile.isEmpty()) {
				String path2 = "upload/company/" + imagePhotoFile.getOriginalFilename();
				String filePath2 = request.getSession().getServletContext().getRealPath("/") + path2;
				File saveDir2 = new File(filePath2);
				if (!saveDir2.getParentFile().exists()) {
					saveDir2.getParentFile().mkdirs();
				}
				// 转存公司形象照片
				imagePhotoFile.transferTo(saveDir2);
				company.setImagePhoto(path2);
			} else {
				company.setImagePhoto(imagePhotoUrl);
			}

			String id = request.getParameter("id");
			if (id == null && !"".equals(id)) {
				return "fail";
			}
			String companyName = request.getParameter("companyName");
			String phone = request.getParameter("phone");
			String fax = request.getParameter("fax");
			String address = request.getParameter("address");
			String qq = request.getParameter("qq");
			String mail = request.getParameter("mail");
			String microblog = request.getParameter("microblog");
			String wechat = request.getParameter("wechat");

			company.setId(Integer.parseInt(id));
			company.setCompanyName(companyName);
			company.setPhone(phone);
			company.setFax(fax);
			company.setAddress(address);
			company.setQq(qq);
			company.setMail(mail);
			company.setMicroblog(microblog);
			company.setWechat(wechat);
			company.setUpdateTime(time);
			int code = this.companyService.updateCompany(company);
			if (code > 0) {
				for (int i = 0; i < mapFile.length; i++) {
					if (!mapFile[i].isEmpty()) {
						String path3 = "upload/company/" + mapFile[i].getOriginalFilename();
						String filePath3 = request.getSession().getServletContext().getRealPath("/") + path3;
						File saveDir3 = new File(filePath3);
						if (!saveDir3.getParentFile().exists()) {
							saveDir3.getParentFile().mkdirs();
						}
						// 转存地图
						mapFile[i].transferTo(saveDir3);

						CompanyImg companyImg = new CompanyImg();
						companyImg.setId(Integer.parseInt(mapId[i]));
						companyImg.setCompanyId(Integer.parseInt(id));
						companyImg.setImgUrl(path3);
						companyImg.setUpdateTime(time);
						this.companyImgService.updateCompanyImg(companyImg);
						companyImgList.add(companyImg);
					} else {
						CompanyImg companyImg = new CompanyImg();
						companyImg.setId(Integer.parseInt(mapId[i]));
						companyImg.setCompanyId(Integer.parseInt(id));
						companyImg.setImgUrl(mapUrl[i]);
						companyImg.setUpdateTime(time);
						companyImgList.add(companyImg);
					}
				}

				model.addAttribute("tip", "修改成功");
			} else {
				model.addAttribute("tip", "修改失败");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		company.setCompanyImgList(companyImgList);
		model.addAttribute("company", company);
		return "update_company";
	}

	/* 删除公司信息 */
	@RequestMapping("/deleteCompany")
	public void deleteCompany(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			session.setAttribute("login", "a");
			if (session.getAttribute("login") == null) {

				response.getOutputStream().write("请先登入".toString().getBytes("utf-8"));

			} else {
				String id = request.getParameter("id");
				if (id != null && !"".equals(id)) {
					this.companyImgService.deleteByCompanyId(Integer.parseInt(id.trim()));
					int code = this.companyService.deleteCompany(Integer.parseInt(id.trim()));
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