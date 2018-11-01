package com.ry.szq.dao;

import java.util.ArrayList;

import com.ry.szq.pojo.Email;

public interface EmailMapper {
		//增删改查方法的实现
		//增加
		 int insert(Email email); 
		//删除(根据主键)
		 int delete(int id);
		//修改
		 int update(Email email);
		//查找
		//查找单个
		 Email selectByID(int id);
		//查找全部
		ArrayList<Email> selectAll();
		//模糊查找
		ArrayList<Email> selectAllByCondition(Email email);
		//根据日期，姓名，主题，内容 查找Email 要ID
		Email selectByFour(String emailName,String emailTime,String emailTheme,String emailContent);
		
}
