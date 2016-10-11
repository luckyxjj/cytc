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
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String time = format.format(new Date());

	/* 通过sendNo和receiverNo获取留言信息 */
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
			List<Message> messageList = this.messageService.getMsgList();
			if (messageList != null) {
				response.getOutputStream().write(JSON.toJSONString(messageList).getBytes("utf-8"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 添加留言 */
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

			int code = this.messageService.addMessage(message);
			if (code > 0) {
				response.getOutputStream().write(JSON.toJSONString(message).getBytes());
			} else {
				response.getOutputStream().write("添加失败".getBytes("utf-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}