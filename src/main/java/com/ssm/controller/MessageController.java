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
import com.ssm.pojo.Message;
import com.ssm.service.IMessageService;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Resource
	private IMessageService messageService;

	// 获取当前时间
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String time = format.format(new Date());

	/* 用户通过sendNo和receiverNo获取留言信息 */
	@RequestMapping("/getMessage")
	public void getMessage(HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session) {
		// 设置服务器端响应的编码格式
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String sendNo = request.getParameter("sendNo");
			String receiverNo = request.getParameter("receiverNo");
			if (sendNo != null && receiverNo != null) {
				Message message = new Message();
				message.setSendNo(sendNo);
				message.setReceiverNo(receiverNo);
				List<Message> messageList = this.messageService.getBySendIdAndReceId(message);
				if (messageList.size() > 0) {
					response.getOutputStream().write(JSON.toJSONString(messageList).getBytes("utf-8"));

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 管理员通过sendNo和receiverNo获取留言信息 */
	@RequestMapping("/getMessageAdm")
	public void getMessageAdm(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		// 设置服务器端响应的编码格式
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String sendNo = request.getParameter("sendNo");
			String receiverNo = request.getParameter("receiverNo");
			String unreadId = request.getParameter("unreadId");

			if (sendNo != null && receiverNo != null) {
				// 修改未读状态
				if (unreadId != null) {
					String[] unreadArray = unreadId.split(",");
					int[] un = new int[unreadArray.length];
					for (int j = 0; j < unreadArray.length; j++) {
						un[j] = Integer.parseInt(unreadArray[j]);
					}
					Message message = new Message();
					message.setUnId(un);
					message.setSendNo(receiverNo);
					message.setUpdateTime(time);
					this.messageService.updateFlag(message);
				}

				Message message = new Message();
				message.setSendNo(sendNo);
				message.setReceiverNo(receiverNo);
				message.setFlag("T");

				List<Message> messageList = this.messageService.getBySendIdAndReceId(message);
				if (messageList.size() > 0) {
					response.getOutputStream().write(JSON.toJSONString(messageList).getBytes("utf-8"));

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 获取留言列表 */
	@RequestMapping("/getMessageList")
	public void getMessageList(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		// 设置服务器端响应的编码格式
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String admId = request.getParameter("admId");
			if (admId != null && !"".equals(admId)) {
				List<Message> messageList = this.messageService.getMsgList();
				if (messageList.size() > 0) {
					// 获取列表中每一项的未读消息数
					for (Message message : messageList) {
						String uNo = "";
						if (message.getSendNo().equals(admId)) {
							uNo = message.getReceiverNo();
						} else {
							uNo = message.getSendNo();
						}
						List<Integer> unreadId = this.messageService.getUnreadNum(uNo);
						message.setUnreadId(unreadId);
						message.setUnreadNum(unreadId.size());

					}
					response.getOutputStream().write(JSON.toJSONString(messageList).getBytes("utf-8"));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 用户添加留言 */
	@RequestMapping("/addMessage")
	public void addMessage(HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session) {
		// 设置服务器端响应的编码格式
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String sendNo = request.getParameter("sendNo");
			String receiverNo = request.getParameter("receiverNo");
			String content = request.getParameter("content");
			Message message = new Message();
			message.setSendNo(sendNo);
			message.setReceiverNo(receiverNo);
			message.setContent(content);
			message.setCreateTime(time);
			message.setFlag("F");

			int code = this.messageService.addMessage(message);
			if (code > 0) {
				response.getOutputStream().write("添加成功".getBytes("utf-8"));
			} else {
				response.getOutputStream().write("添加失败".getBytes("utf-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* 管理员添加留言 */
	@RequestMapping("/addMessageAdm")
	public void addMessageAdm(HttpServletRequest request, Model model, HttpServletResponse response,
			HttpSession session) {
		// 设置服务器端响应的编码格式
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		try {
			/*
			 * if (session.getAttribute("login") == null) { return "login"; }
			 */
			String sendNo = request.getParameter("sendNo");
			String receiverNo = request.getParameter("receiverNo");
			String content = request.getParameter("content");
			Message message = new Message();
			message.setSendNo(sendNo);
			message.setReceiverNo(receiverNo);
			message.setContent(content);
			message.setCreateTime(time);

			int code = this.messageService.addMessage(message);
			if (code > 0) {
				response.getOutputStream().write("添加成功".getBytes("utf-8"));
			} else {
				response.getOutputStream().write("添加失败".getBytes("utf-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}