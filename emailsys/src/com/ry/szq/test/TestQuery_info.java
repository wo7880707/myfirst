package com.ry.szq.test;

import java.sql.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ry.szq.pojo.Query_info;
import com.ry.szq.service.Query_infoService;

public class TestQuery_info {
	@Test
	public void test(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Query_infoService q = (Query_infoService)ac.getBean("query_infoService");
		System.out.println(q.queryByInfo("2"));
		//q.insert(new Query_info(3,3,new Date(1213321210),"123","456","99","99","00"));
		System.out.println(q.queryByInfo("3"));
		//System.out.println(q.queryByInfo(4));
	}
}