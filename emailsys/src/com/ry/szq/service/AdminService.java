package com.ry.szq.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ry.szq.dao.AdminMapper;
import com.ry.szq.pojo.Admin;

@Service
public class AdminService {
	@Autowired
	private AdminMapper adminmapper;

	public AdminMapper getAdminmapper() {
		return adminmapper;
	}

	public void setAdminmapper(AdminMapper adminmapper) {
		this.adminmapper = adminmapper;
	}

	 //登录
    public boolean register(Admin admin){
    	boolean boo = false;
    	Admin a = adminmapper.register(admin);
    	System.out.println(a);
    	if(a != null){
    		boo = true;
    	}
    System.out.println("------------------------------");
    System.out.println(boo  + "------------------------------");
    System.out.println("------------------------------");
    	return boo;
    }
	// 增加
	public int insert(Admin admin) {
		return adminmapper.insert(admin);
	}

	// 修改
	public int update(Admin admin) {
		return adminmapper.update(admin);
	}

	// 删除
	public int delete(String userID) {
		return adminmapper.delete(userID);
	}

	// 查询
	// 查询单个
	public Admin selectByID(String userID) {
		return adminmapper.selectByID(userID);
	}

	// 查找全部
	public ArrayList<Admin> selectAll() {
		return adminmapper.selectAll();
	}
	//查找全部 :状态是3的
	public	ArrayList<Admin> selectAllBy3(){
		return adminmapper.selectAllBy3();
	}
	//查找全部 :状态是2的
		public	ArrayList<Admin> selectAllBy2(){
			return adminmapper.selectAllBy2();
		}
	
}
