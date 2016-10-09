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
import com.ssm.pojo.Production;
import com.ssm.pojo.ProductionImg;
import com.ssm.service.IProductionImgService;
import com.ssm.service.IProductionService;

@Controller
@RequestMapping("/production")
public class ProductionController {

	@Resource
	private IProductionService productionService;

	@Resource
	private IProductionImgService productionImgService;

	// 获取当前时间
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String time = format.format(new Date());

	/* 获取所有作品信息 */
	@RequestMapping("/getProduction")
	public String getProduction(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			List<Production> productionList = productionService.getAllProduction();
			if (productionList.size() > 0) {
				for (Production production : productionList) {
					List<ProductionImg> productionImg = this.productionImgService.getByProductionId(production.getId());
					production.setProductionImgList(productionImg);
				}
				model.addAttribute("productionList", productionList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "production";
	}

	/* 获取作品信息 */
	@RequestMapping("/getProductionInfo")
	public void getProductiontInfo(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		// 设置服务器端响应的编码格式
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			List<Production> productionList = productionService.getAllProduction();
			if (productionList.size() > 0) {
				for (Production production : productionList) {
					List<ProductionImg> productionImg = this.productionImgService.getByProductionId(production.getId());
					production.setProductionImgList(productionImg);
				}
				response.getOutputStream().write(JSON.toJSONString(productionList).getBytes("utf-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* 通过id获取作品信息 */
	@RequestMapping("/getProductionById")
	public String getProductionById(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String id = request.getParameter("id");
			if (id != null && !"".equals(id)) {
				Production production = this.productionService.getProductionById(Integer.parseInt(id.trim()));
				List<ProductionImg> productionImgList = this.productionImgService
						.getByProductionId(Integer.parseInt(id));
				production.setProductionImgList(productionImgList);
				model.addAttribute("production", production);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "update_production";
	}

	/* 添加作品信息 */
	@RequestMapping("/addProduction")
	public String addProduction(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session, @RequestParam("productionFile") MultipartFile[] productionFile) {
		response.setCharacterEncoding("utf-8");
		try {

			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */

			if (productionFile.length > 0) {
				String description = request.getParameter("description");
				String category = request.getParameter("category");
				Production production = new Production();
				production.setCategory(category);
				production.setDescription(description);
				production.setCreateTime(time);
				int code = this.productionService.addProduction(production);
				if (code > 0) {

					for (int i = 0; i < productionFile.length; i++) {
						if (!productionFile[i].isEmpty()) {
							String path = "upload/production/" + productionFile[i].getOriginalFilename();
							String filePath = request.getSession().getServletContext().getRealPath("/") + path;
							File saveDir = new File(filePath);
							if (!saveDir.getParentFile().exists()) {
								saveDir.getParentFile().mkdirs();
							}
							// 转存图片
							productionFile[i].transferTo(saveDir);

							ProductionImg productionImg = new ProductionImg();
							productionImg.setImgUrl(path);
							productionImg.setProductionId(production.getId());
							productionImg.setCreateTime(time);
							this.productionImgService.addProductionImg(productionImg);

						}
					}
					model.addAttribute("tip", "添加成功");

				} else {
					model.addAttribute("tip", "添加失败");
				}

			} else {
				model.addAttribute("tip", "请选择图片");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "add_production";
	}

	/* 修改作品信息 */
	@RequestMapping("/updateProduction")
	public String updateProduction(HttpServletRequest request, Model model, HttpServletResponse response,
			@RequestParam("productionFile") MultipartFile[] productionFile, @RequestParam("imgId") String[] imgId,
			@RequestParam("imgUrl") String[] imgUrl) {
		Production production = new Production();
		List<ProductionImg> productionImgList = new ArrayList<ProductionImg>();
		try {
			response.setCharacterEncoding("utf-8");
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			if (productionFile.length > 0) {
				String id = request.getParameter("id");
				if (id == null && !"".equals(id)) {
					return "fail";
				}
				String category = request.getParameter("category");
				String description = request.getParameter("description");

				production.setId(Integer.parseInt(id));
				production.setCategory(category);
				production.setDescription(description);
				production.setUpdateTime(time);
				int code = this.productionService.updateProduction(production);
				if (code > 0) {

					for (int i = 0; i < productionFile.length; i++) {
						if (!productionFile[i].isEmpty()) {
							String path = "upload/production/" + productionFile[i].getOriginalFilename();
							String filePath = request.getSession().getServletContext().getRealPath("/") + path;
							File saveDir = new File(filePath);
							if (!saveDir.getParentFile().exists()) {
								saveDir.getParentFile().mkdirs();
							}
							// 转存图片
							productionFile[i].transferTo(saveDir);

							ProductionImg productionImg = new ProductionImg();
							productionImg.setId(Integer.parseInt(imgId[i]));
							productionImg.setProductionId((Integer.parseInt(id)));
							productionImg.setImgUrl(path);
							productionImg.setUpdateTime(time);
							this.productionImgService.updateProductionImg(productionImg);
							productionImgList.add(productionImg);

						} else {
							ProductionImg productionImg = new ProductionImg();
							productionImg.setId(Integer.parseInt(imgId[i]));
							productionImg.setImgUrl(imgUrl[i]);
							productionImgList.add(productionImg);
						}
					}

					model.addAttribute("tip", "修改成功");

				} else {
					model.addAttribute("tip", "修改失败");
					productionImgList = this.productionImgService.getByProductionId(Integer.parseInt(id));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		production.setProductionImgList(productionImgList);
		model.addAttribute("production", production);
		return "update_production";
	}

	/* 删除作品信息 */
	@RequestMapping("/deleteProduction")
	public void deleteProduction(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			session.setAttribute("login", "a");
			if (session.getAttribute("login") == null) {
				response.getOutputStream().write("请先登入".toString().getBytes("utf-8"));
			} else {
				String id = request.getParameter("id");
				if (id != null && !"".equals(id)) {
					this.productionImgService.deleteByProductionId(Integer.parseInt(id));
					int code = this.productionService.deleteProduction(Integer.parseInt(id));
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