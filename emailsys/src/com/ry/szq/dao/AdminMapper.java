package com.ry.szq.dao;

import java.util.ArrayList;

import com.ry.szq.pojo.Admin;

public interface AdminMapper {
	//增删改查方法的实现
	//增加
	 int insert(Admin admin); 
	//删除(根据主键)
	 int delete(String userID);
	//修改
	 int update(Admin admin);
	//查找
	//查找单个
	 Admin selectByID(String userID);
	//查找全部
	ArrayList<Admin> selectAll();
	//查找全部 :状态是3的
	ArrayList<Admin> selectAllBy3();
	//查找全部 :状态是2的
		ArrayList<Admin> selectAllBy2();
	//登录
	Admin register(Admin admin);
	// 查找单个 ：根据remark查询
	Admin selectByUserRemark(String userRemark);
}
