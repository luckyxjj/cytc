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
import com.ssm.pojo.Activity;
import com.ssm.pojo.ActivityImg;
import com.ssm.service.IActivityImgService;
import com.ssm.service.IActivityService;

@Controller
@RequestMapping("/activity")
public class ActivityController {

	@Resource
	private IActivityService activityService;

	@Resource
	private IActivityImgService activityImgService;

	// 获取当前时间
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String time = format.format(new Date());

	/* 获取所有活动信息 */
	@RequestMapping("/getActivity")
	public String getActivity(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			List<Activity> activityList = activityService.getAllActivity();
			if (activityList.size() > 0) {
				model.addAttribute("activityList", activityList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "activity";
	}

	/* 获取活动信息 */
	@RequestMapping("/getActivityInfo")
	public void getActivityInfo(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		// 设置服务器端响应的编码格式
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			List<Activity> activityList = activityService.getAllActivity();
			if (activityList.size() > 0) {
				model.addAttribute("activityList", activityList);
				response.getOutputStream().write(JSON.toJSONString(activityList).getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* 通过id获取活动信息 */
	@RequestMapping("/getActivityById")
	public void getActivityById(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String id = request.getParameter("id");
			if (id != null && !"".equals(id)) {
				Activity activity = this.activityService.getActivityById(Integer.parseInt(id.trim()));
				List<ActivityImg> activityImgList = this.activityImgService
						.getByActivityId(Integer.parseInt(id.trim()));
				activity.setActivityImgList(activityImgList);
				if (activity != null) {
					response.getOutputStream().write(JSON.toJSONString(activity).getBytes());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* 通过id获取活动信息 for back */
	@RequestMapping("/getActivityById2")
	public String getActivityById2(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String id = request.getParameter("id");
			if (id != null && !"".equals(id)) {
				Activity activity = this.activityService.getActivityById(Integer.parseInt(id.trim()));
				List<ActivityImg> activityImgList = this.activityImgService
						.getByActivityId(Integer.parseInt(id.trim()));
				activity.setActivityImgList(activityImgList);
				if (activity != null) {
					model.addAttribute("activity", activity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "update_activity";
	}

	/* 添加活动信息 */
	@RequestMapping("/addActivity")
	public String addActivity(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session, @RequestParam("imgUrl") MultipartFile imgUrl,
			@RequestParam("activityFile") MultipartFile[] activityFile) {
		response.setCharacterEncoding("utf-8");
		try {

			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			if (activityFile.length > 0 && imgUrl != null) {
				String introduce = request.getParameter("introduce");
				String detailedIntroduce = request.getParameter("detailedIntroduce");

				Activity activity = new Activity();
				activity.setIntroduce(introduce);
				activity.setDetailedIntroduce(detailedIntroduce);
				String path1 = "upload/activity/" + imgUrl.getOriginalFilename();
				String filePath1 = request.getSession().getServletContext().getRealPath("/") + path1;
				File saveDir1 = new File(filePath1);
				if (!saveDir1.getParentFile().exists()) {
					saveDir1.getParentFile().mkdirs();
				}
				// 转存图片
				imgUrl.transferTo(saveDir1);
				activity.setImgUrl(path1);
				activity.setCreateTime(time);

				int id = this.activityService.addActivity(activity);
				if (id > 0) {
					for (MultipartFile multipartFile : activityFile) {
						if (!multipartFile.isEmpty()) {
							String path = "upload/activity/" + multipartFile.getOriginalFilename();
							String filePath = request.getSession().getServletContext().getRealPath("/") + path;
							File saveDir = new File(filePath);
							if (!saveDir.getParentFile().exists()) {
								saveDir.getParentFile().mkdirs();
							}
							// 转存图片
							multipartFile.transferTo(saveDir);
							ActivityImg activityImg = new ActivityImg();
							activityImg.setActivityId(activity.getId());
							activityImg.setImgUrl(path);
							activityImg.setCreateTime(time);
							this.activityImgService.addActivityImg(activityImg);
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

		return "add_activity";
	}

	/* 修改活动信息 */
	@RequestMapping("/updateActivity")
	public String updateActivity(HttpServletRequest request, Model model, HttpServletResponse response,
			@RequestParam("activityFile") MultipartFile activityFile,
			@RequestParam("activityImgFile") MultipartFile[] activityImgFile, @RequestParam("imgId") String[] imgId,
			@RequestParam("imgUrl") String[] imgUrl, @RequestParam("imgUrl2") String[] imgUrl2) {
		Activity activity = new Activity();
		List<ActivityImg> activityImgList = new ArrayList<ActivityImg>();
		try {
			response.setCharacterEncoding("utf-8");
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			if (!activityFile.isEmpty()) {
				String path = "upload/activity/" + activityFile.getOriginalFilename();
				String filePath = request.getSession().getServletContext().getRealPath("/") + path;
				File saveDir = new File(filePath);
				if (!saveDir.getParentFile().exists()) {
					saveDir.getParentFile().mkdirs();
				}
				// 转存图片
				activityFile.transferTo(saveDir);
				activity.setImgUrl(path);

			}
			String id = request.getParameter("id");
			if (id == null && !"".equals(id)) {
				return "fail";
			}
			String introduce = request.getParameter("introduce");
			String detailedIntroduce = request.getParameter("detailedIntroduce");

			activity.setId(Integer.parseInt(id));
			activity.setIntroduce(introduce);
			activity.setDetailedIntroduce(detailedIntroduce);
			activity.setUpdateTime(time);
			int code = this.activityService.updateActivity(activity);
			if (code > 0) {
				if (activityImgFile.length > 0) {
					for (int i = 0; i < activityImgFile.length; i++) {
						if (!activityImgFile[i].isEmpty()) {
							String path = "upload/activity/" + activityImgFile[i].getOriginalFilename();
							String filePath = request.getSession().getServletContext().getRealPath("/") + path;
							File saveDir = new File(filePath);
							if (!saveDir.getParentFile().exists()) {
								saveDir.getParentFile().mkdirs();
							}
							// 转存图片
							activityImgFile[i].transferTo(saveDir);
							ActivityImg activityImg = new ActivityImg();
							activityImg.setId(Integer.parseInt(imgId[i]));
							activityImg.setImgUrl(path);
							activityImg.setUpdateTime(time);
							int code2 = this.activityImgService.updateActivityImg(activityImg);
							if (code2 > 0) {
								activityImgList.add(activityImg);
							} else {
								ActivityImg activityImg2 = new ActivityImg();
								activityImg2.setId(Integer.parseInt(imgId[i]));
								activityImg2.setImgUrl(imgUrl[i]);
								activityImgList.add(activityImg2);
							}

						} else {

							ActivityImg activityImg = new ActivityImg();
							activityImg.setId(Integer.parseInt(imgId[i]));
							activityImg.setImgUrl(imgUrl[i]);
							activityImgList.add(activityImg);
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
		activity.setActivityImgList(activityImgList);
		model.addAttribute("activity", activity);
		return "update_activity";
	}

	/* 删除活动信息 */
	@RequestMapping("/deleteActivity")
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
					int imgCode = this.activityImgService.deleteByActivityId(Integer.parseInt(id));
					if (imgCode > 0) {
						int code = this.activityService.deleteActivity(Integer.parseInt(id.trim()));
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