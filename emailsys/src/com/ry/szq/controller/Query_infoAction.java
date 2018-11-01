package com.ry.szq.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.szq.pojo.Admin;
import com.ry.szq.pojo.Query_info;
import com.ry.szq.service.Query_infoService;

@Controller
@RequestMapping("/query_info")
public class Query_infoAction {
	@Autowired
	private Query_infoService query_infoService ;

	public Query_infoService getQuery_infoService() {
		return query_infoService;
	}

	public void setQuery_infoService(Query_infoService query_infoService) {
		this.query_infoService = query_infoService;
	}
	//增加
	@RequestMapping("/insert.action")
	public @ResponseBody int insert(Query_info query_info){
		return query_infoService.insert(query_info);
	}
	//查询
	@RequestMapping("/query.action")
	public @ResponseBody Query_info queryByInfo(HttpServletRequest request){
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		String userID = admin.getUserID();
		return query_infoService.queryByInfo(userID);
		}
}
