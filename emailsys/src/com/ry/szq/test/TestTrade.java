package com.ry.szq.test;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ry.szq.common.Pager;
import com.ry.szq.pojo.Trade;
import com.ry.szq.service.TradeService;

public class TestTrade {
	@Test
	public void test(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		TradeService e = (TradeService)ac.getBean("tradeService");
		 ArrayList<Trade> list = e.highcharts_byclName("pp1801","GH01", "070043", "20170428", "20170828");
			for (Trade trade : list) {
				System.out.println(trade.toString());
			}
		 /*Map<String,Object> map = e.selectAllByTime("20170412", "20170414",2);
		 ArrayList<Trade> list = (ArrayList<Trade>)map.get("trade");
		for (Trade trade : list) {
			System.out.println(trade.toString());
		}*/
		/*Pager pager = new Pager(5416,2);
		ArrayList<Trade> list = e.selectAllByPaper(pager);
		for (Trade trade : list) {
			System.out.println(trade);
		}*/
		/*ArrayList<Trade> list = e.selectCL2("095386", "pp1709", "GH02", "20170706");
		for (Trade string : list) {
			System.out.println(string);
		}*/
		//int i = e.recordCount();

		/*BigDecimal b1 = new BigDecimal(Double.toString(temp)); 
    	BigDecimal b2 = new BigDecimal("5645.21");
    	System.out.println(b1.add(b2).doubleValue() + "");*/
		/*String str = ".[GL08].null.[GH02].[GL01].[GL03].[GL05].[GL04].[GL07].[GL06]";
		String [] a = str.split("\\.");
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}*/
		//int i = e.insert(new Trade(2,"das","dsad","dasd","dsad","asd","dsad","sad","dassd","asdas","dsad","dsad","dsad","sa","ds","s"));
		/*ArrayList<Trade> list = e.selectCL("095386", "pp1709", "[GH02]");
		for (Trade trade : list) {
			System.out.println(trade.toString());
		}*/
		//1499097600000 1499097600000
		/*SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Trade trade = e.selectByID(123);
		String a = trade.getTime();
		System.out.println(a);
		Date date = format.parse(a);
		System.out.println(date.getTime());
		System.out.println("-------------------");
		
		long i =  1499097600000l; 
		Date date2 = new Date(i);
		System.out.println(date2);
		System.out.println(date2.getTime());
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(format1.format(date2));
		
		System.out.println("-------------------");
		System.out.println("-------------------");
		System.out.println("-------------------");
		System.out.println("-------------------");*/
		
		//e.selectAll();
		
	}
}
