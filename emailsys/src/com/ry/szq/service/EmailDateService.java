package com.ry.szq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ry.szq.dao.EmailDateMapper;
import com.ry.szq.pojo.EmailDate;

@Service
public class EmailDateService {
	@Autowired
	private EmailDateMapper emailDateMapper;

	public EmailDateMapper getEmailDateMapper() {
		return emailDateMapper;
	}

	public void setEmailDateMapper(EmailDateMapper emailDateMapper) {
		this.emailDateMapper = emailDateMapper;
	}
	//增加
	public int insert(EmailDate date){
		return emailDateMapper.insert(date);
	}
	
		//修改
	public int update(EmailDate date){
		return emailDateMapper.update(date);
	}
	//查询
	public EmailDate query(){
		return emailDateMapper.query();
	}
}
