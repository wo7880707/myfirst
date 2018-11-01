package com.ry.szq.test;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ry.szq.pojo.EmailDate;
import com.ry.szq.service.EmailDateService;

public class TestEmailDate {
	@Test
	public void test(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmailDateService e = (EmailDateService)ac.getBean("emailDateService");
		EmailDate da = e.query();
		System.out.println(da.toString());
		//System.out.println(e.insert(new EmailDate(1,new Date())));
		//e.insert(new EmailDate(2,new Date()));
		//System.out.println(e.query());
		//Date date = e.query().getDate();
		//System.out.println(date);
		//System.out.println(date.getTime());
		/*Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		e.insert(new EmailDate(3,timeStamp));*/
		
		
	}
}
