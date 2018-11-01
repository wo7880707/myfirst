package com.ry.szq.test;

import java.util.ArrayList;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ry.szq.pojo.Email;
import com.ry.szq.service.EmailService;

public class TestEmailLike {
	@Test
	public void test()throws Exception{
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmailService e = (EmailService)ac.getBean("emailService");
		Email email = new Email(5,"快",null,"最",null,"D","");
		ArrayList<Email> a = e.selectAllByCondition(email);
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i));
		}
	}
}
