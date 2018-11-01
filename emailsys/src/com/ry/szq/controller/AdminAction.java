package com.ry.szq.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.szq.pojo.Admin;
import com.ry.szq.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminAction {
	@Autowired
	private AdminService adminService;

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	// 查询单个
	@RequestMapping("/selectByID.action")
	public @ResponseBody Admin selectByID(String userID) {
		return adminService.selectByID(userID);
	}
	// 查询所有 是3 的人
	@RequestMapping("/selectAllBy3.action")
	public @ResponseBody ArrayList<Admin> selectAllBy3() {
		return adminService.selectAllBy3();
	}
	// 查询所有 是2的人
		@RequestMapping("/selectAllBy2.action")
		public @ResponseBody ArrayList<Admin> selectAllBy2() {
			return adminService.selectAllBy2();
		}
	//通过验证  将3变成2
	@RequestMapping("/updateAdmin.action")
	public String updateAdmin(String userID) {
		Admin admin = adminService.selectByID(userID);
		Admin admin2 = new Admin(admin.getUserID(),admin.getUserPass(),admin.getUserGender(),admin.getUserName(),"2");
		adminService.update(admin2);
		return "redirect:/addadmin.html";
	}
	//通过验证  将2变成3
	@RequestMapping("/updateAdminTwo.action")
	public String updateAdminTwo(String userID) {
			Admin admin = adminService.selectByID(userID);
			Admin admin2 = new Admin(admin.getUserID(),admin.getUserPass(),admin.getUserGender(),admin.getUserName(),"3");
			adminService.update(admin2);
			return "redirect:/showalladmin.html";
		}
	@RequestMapping("/insert.action")
	public @ResponseBody int insert(String userID, String userPass ,String userGender, String userName  ) {
		//Admin admin1 = adminService.selectByID(userID);
		System.out.println("----------------------------------");
		System.out.println("----------------------------------");
		//String a = request.getParameter("userID");
		System.out.println("---------" + userID  + userPass +userGender +  userName);
		System.out.println("----------------------------------");
		System.out.println("----------------------------------");
		Admin admin = new Admin(userID,userPass,userGender,userName,"3");
		return adminService.insert(admin);
	}
	//登录   true 成功 false 失败
	@RequestMapping("/register.action")
	public @ResponseBody int register(Admin admin,HttpServletRequest request) {
		int i = 0;
		//登陆成功 1 2 3  登陆不成功 0
		if(adminService.register(admin)){
			//获取整个对象
			Admin admin2 = adminService.selectByID(admin.getUserID());
			if(admin2.getUserRemark().equals("1")){
				request.getSession().setAttribute("admin", admin2);
				i = 1;
			} else if(admin2.getUserRemark().equals("2")){
				request.getSession().setAttribute("admin", admin2);
				i = 2;
			} else{
				i = 3;
			}
		}
		return i;
	}
	//是否有session登录
	@RequestMapping("/ifSessionHas.action")
	public @ResponseBody Admin register(HttpServletRequest request){
		Admin admin = (Admin)request.getSession().getAttribute("admin") ;
		//System.out.println(admin);
		return admin;
	}

	//推出session登录
		@RequestMapping("/destroySession.action")
		public  String  destroy(HttpServletRequest request){
			request.getSession().invalidate();
			return "redirect:/login.html";
		}
	
}
