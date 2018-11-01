package com.ry.szq.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ry.szq.pojo.Count;
import com.ry.szq.service.CountService;

public class TestCount {
	@Test
	public void test(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		CountService e = (CountService)ac.getBean("countService");
		//System.out.println(e.insert(new Admin("2","123456")));
		System.out.println(e.update(new Count(1,5)));
		Count c = e.queryById(1);
		System.out.println(c);
	}
}
