package com.ry.szq.test;

import java.util.Arrays;

import org.junit.Test;

public class TestAdmin {
	@Test
	public void test(){
		/*ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		AdminService e = (AdminService)ac.getBean("adminService");
		//System.out.println(e.insert(new Admin("2","123456")));
		boolean b = e.register(new Admin("2","123456"));*/
		String[] imgsNames = {"b3","a2","ba","a1"};
		Arrays.sort(imgsNames);
		for (int i = 0; i < imgsNames.length; i++) {
			System.out.println(imgsNames[i]);
		}
	}
}
