package com.ry.szq.dao;


import com.ry.szq.pojo.EmailDate;

public interface EmailDateMapper {
	//增加
	int insert(EmailDate date);
	//修改
	int update(EmailDate date);
	//查询
	EmailDate query();
}
