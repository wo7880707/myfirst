package com.ry.szq.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysql.jdbc.Blob;
import com.ry.szq.pojo.Email;
import com.ry.szq.service.EmailService;

import sun.misc.BASE64Decoder;

public class TestGet {
	@Test
	public void test()throws Exception{
		/*String str = "   529.72平仓盈亏 : 650.00持仓盈亏 : 0.00浮动收益 : 620.28最新价   : 7917.00多持仓   : 0空持仓   : 0手续费   : 29.72   ";
		String regex1 = "[\\u4e00-\\u9fa5]";
		String shuzi = str.replaceAll(regex1, "");
		System.out.println(shuzi);*/
		/*String str = "    dfasdas  sdsad   ";
		System.out.println(str.trim());*/
		String str = "-[GL08]-null-[GH02]-[GL01]-[GL03]-[GL05]-[GL04]-[GL07]-[GL06]";
		String [] a = str.split("-");

		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);

		}
		/*String s = "123阿三抠脚大汉啊哈说的看.456哈哈441";
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]+|\\d+");
        Matcher m = p.matcher( s );
         m.find() ;
         System.out.println(m.group());
         m.find();
             System.out.println(m.group());*/
        
    
		/*ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmailService e = (EmailService)ac.getBean("emailService");
		//图片
		
		Email a1 = e.selectByID(1);
		//Blob b = a1.getEmailFile();
		System.out.println(a1);
		System.out.println("--------------------");
		System.out.println(a1.getEmailFile().toString());*/
		/*byte[] b = a1.getEmailFile().getBytes();
		System.out.println(b.toString());
		Blob blob = null;
		new String(blob.getBytes((long)1, (int)blob.length()));
		//解码
		String base64 = a1.getEmailFile().toString();

		//byte[] b = null;
		String result = null;
		BASE64Decoder decoder = new BASE64Decoder();  
        try {  
            b = decoder.decodeBuffer(base64);  
            result = new String(b, "utf-8");  
        } catch (Exception a) {  
            a.printStackTrace();  
        }  
        System.out.println(result);*/
		//System.out.println(a1.getEmailFile());
		/*Blob blob = null;  
		  
	    try{  
	         String content = new String(blob.getBytes((long)1, (int)blob.length()));  
	         System.out.println(content);
	    } catch(SQLException a) {  
	         a.printStackTrace();  
	    }  */
		//Blob b = a1.getEmailFile();
		System.out.println("--------------------");
		//byte[] data = null;
		
        
        /*// 读取图片字节数组  
        try {  
            //BufferedInputStream in = new BufferedInputStream(a1.getEmailFile());  
           // data = new byte[in.available()];  
           // in.read(data);  
           // in.close();  
        } catch (IOException i) {  
            i.printStackTrace();  
        }  */
		//文件
		//Email a2 = e.selectByID(326);
	}
}
