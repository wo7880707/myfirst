package com.ry.szq.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ry.szq.dao.EmailMapper;
import com.ry.szq.pojo.Email;

@Service
public class EmailService {

	@Autowired
	private EmailMapper emailMapper;

	public EmailMapper getEmailMapper() {
		return emailMapper;
	}

	public void setEmailMapper(EmailMapper emailMapper) {
		this.emailMapper = emailMapper;
	}
	//增加
	public int insert(Email email){
		return emailMapper.insert(email);
	}
	//修改
	public int update(Email email){
		return emailMapper.update(email);
	}
	//删除
	public int delete(int id){
		return emailMapper.delete(id);
	}
	//查询
	//查询单个
	public Email selectByID(int id){
		 return emailMapper.selectByID(id);
	 }
	//根据日期，姓名，主题，内容 查找Email 要ID
	public Email selectByFour(String emailName ,String emailTime,String emailTheme,String emailContent){
		 return emailMapper.selectByFour(emailName, emailTime, emailTheme, emailContent);
	}
	//查找全部
	public ArrayList<Email> selectAll(){
		return emailMapper.selectAll();
	}
	//模糊查找
	public ArrayList<Email> selectAllByCondition(Email email){
		return emailMapper.selectAllByCondition(email);
	}

}
