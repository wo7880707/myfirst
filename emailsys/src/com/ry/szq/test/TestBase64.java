package com.ry.szq.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ry.szq.common.ShowMail;
import com.ry.szq.pojo.Email;
import com.ry.szq.pojo.EmailDate;
import com.ry.szq.pojo.Trade;
import com.ry.szq.service.EmailDateService;
import com.ry.szq.service.EmailService;
import com.ry.szq.service.TradeService;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;

public class TestBase64 {
	@Test
	public void test(){
		try {
			ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
			//得到添加的最后时间
			EmailDateService ed = (EmailDateService)ac.getBean("emailDateService");
			EmailService es = (EmailService)ac.getBean("emailService");
			TradeService ts = (TradeService)ac.getBean("tradeService");
			String str = ed.query().getDate();
			SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss"); 
			Date date = format.parse(str);
			//最后存储的时间Long值
			long endTime = date.getTime();
			//网站协议名 账号和密码
			//imap连接
	    	 String user = "songzhiqiang601@163.com";// 邮箱的用户名   
	         String password = "wo7880707"; // 邮箱的密码   
	   
	         Properties prop = System.getProperties();   
	         prop.put("mail.store.protocol", "imap");   
	         prop.put("mail.imap.host", "imap.163.com");   
	   
	         Session session = Session.getInstance(prop);   
	         //http://config.mail.163.com/settings/imap/index.jsp?uid=songzhiqiang601@163.com
	         int total = 0;   
	         // 使用imap会话机制，连接服务器
	         IMAPStore store = (IMAPStore) session.getStore("imap"); 
	         store.connect(user, password); 
	         IMAPFolder folder = (IMAPFolder) store.getFolder("INBOX"); // 收件箱   
	         folder.open(Folder.READ_WRITE);   
	         // 获取总邮件数   
	         total = folder.getMessageCount();   
	         System.out.println("-----------------共有邮件：" + total   
	                 + " 封--------------");   
	         // 得到收件箱文件夹信息，获取邮件列表   
	         System.out.println("未读邮件数：" + folder.getUnreadMessageCount());   
	         Message[] message = folder.getMessages(); 
	        System.out.println("-----------------邮件数量:　" + message.length + " 封--------------");  
	        ShowMail re = (ShowMail)ac.getBean("showMail");
	  
	        for (int i = 0; i < message.length; i++) {  
	            re = new ShowMail((MimeMessage) message[i]); 
	            //邮箱的发送时间
	            Date time = re.getDate();
	            //如果邮件接收时间大于数据库读取的时间  就把信息存储到数据库 
	            if(time.getTime() > endTime){
	            	//设置本地位置 不存储在本地
	            	re.setAttachPath("");  
	            	//判断是否有附件 保存附件 并 存到数据库里
		            if(re.isContainAttach((Part) message[i]) == false){
		            	//获得正文StringBuffer
		            	re.getMailContent((Part) message[i]); 
		            	
		            	//插入数据库
		            	es.insert(new Email(re.getFrom(),re.getSentDate(),re.getSubject(),re.getBodyText()," ",""));
		            	
		            	//同时插入trade
		            	//先得到原始数据的Id
		            	Email email = es.selectByFour(re.getFrom(),re.getSentDate(),re.getSubject(),re.getBodyText());
		            	System.out.println("---------------------------");
		            	System.out.println("---------------------------");
		            	//System.out.println("原素具内容" + email);
		            	System.out.println("---------------------------");
		            	System.out.println("---------------------------");
		            	int id = email.getId();
		            	//设置邮箱为已读
		            	message[i].setFlag(Flags.Flag.SEEN, true);
		            	//更改一下 更改一下数据库时间
		            	ed.update(new EmailDate(1,re.getSentDate()));
		            }else {
		            	//获得正文StringBuffer
		            	

		            	re.getMailContent((Part) message[i]); 
		            	
		            	//保存附件的base64 到数据库
		            	String base64 = re.saveAttachMent((Part) message[i]);
		            	//插入数据库
		            	es.insert(new Email(re.getFrom(),re.getSentDate(),re.getSubject(),re.getBodyText(),re.getName((Part) message[i]),base64));
		            	ed.update(new EmailDate(1,re.getSentDate()));
		            	//同时插入trade
		            	//先得到原始数据的Id
		            	System.out.println("---------------------------");
		            	System.out.println("---------------------------");
		            	//System.out.println("原素具内容" + email);
		            	System.out.println("---------------------------");
		            	System.out.println("---------------------------");
		            	//int id = email.getId();
		            	//id作为主键插入到trade中 还要找出其他的属性字段
		            	//时间
		            	String tradetime = re.getSubject().split(":")[1].split("_")[0];
		            	//白夜
		            	String tradedn = re.getSubject().split(":")[1].split("_")[1];
		            	//杠上开账户
		            	String tradegskCount = re.getSubject().split(":")[1].split("_")[2];
		            	//交易账户
		            	String tradetradeCount = re.getSubject().split(":")[1].split("_")[3];
		            	//策略名
		            	String tradeclName = re.getBodyText().split("]")[0].trim() + "]";
		            	//ID
		            	String tradecsID = "<" + re.getBodyText().split("]")[1].split("<")[1];
		            	//合约
		            	String tradecontract = re.getBodyText().split("]")[1].split("<")[2].split(">")[0];
		            	//汉字的正则
		            	String regex1 = "[\\u4e00-\\u9fa5]";
		        		//String shuzi = str.replaceAll(regex1, "");
		            	//期望收益 : 529.72
		            	String trade1 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[1];
		            	String tradeqwsy = trade1.replaceAll(regex1, "").trim();
		            	//平仓盈亏 : 650.00
		            	String trade2 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[2];
		            	String tradepcyk = trade2.replaceAll(regex1, "").trim();
		            	//持仓盈亏 : 0.00
		            	String trade3 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[3];
		            	String tradeccyk = trade3.replaceAll(regex1, "").trim();
		            	//浮动收益 : 620.28
		            	String trade4 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[4];
		            	String tradefdsy = trade4.replaceAll(regex1, "").trim();
		            	//最新价   : 7917.00
		            	String trade5 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[5];
		            	String tradezxj = trade5.replaceAll(regex1, "").trim();
		            	//多持仓   : 0
		            	String trade6 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[6];		
		            	String tradedcc = trade6.replaceAll(regex1, "").trim();
		            	//空持仓   : 0
		            	String trade7 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[7];
		            	String tradekcc = trade7.replaceAll(regex1, "").trim();
		            	//手续费   : 29.72
		            	String trade8 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[8];
		            	String tradesxf = trade8.replaceAll(regex1, "").trim();
		            	System.out.println("---------------------------");
		            	System.out.println("---------------------------");
		            	//System.out.println("id::" +id);
		            	System.out.println( "tradetime::" +tradetime);
		            	System.out.println( "tradedn::" +tradedn );
		            	System.out.println( "tradegskCount::" +tradegskCount);
		            	System.out.println( "tradetradeCount::" +tradetradeCount);
		            	System.out.println( "tradeclName::" +tradeclName);
		            	System.out.println( "tradecsID::" +tradecsID);
		            	System.out.println( "tradecontract::" +tradecontract);
		            	System.out.println( "tradeqwsy::" +tradeqwsy);
		            	System.out.println( "tradepcyk::" +tradepcyk);
		            	System.out.println( "tradeccyk::" +tradeccyk);
		            	System.out.println( "tradefdsy::" +tradefdsy);
		            	System.out.println( "tradezxj::" +tradezxj);
		            	System.out.println( "tradedcc::" +tradedcc);
		            	System.out.println( "tradekcc::" +tradekcc);
		            	System.out.println( "tradesxf::" +tradesxf);
		            	System.out.println("---------------------------");
		            	System.out.println("---------------------------");
		            	System.out.println("---------------------------");
		            	ts.insert(new Trade(20,tradetime, tradedn, tradegskCount, tradetradeCount, tradeclName, tradecsID, tradecontract, tradeqwsy, tradepcyk, tradeccyk, tradefdsy, tradezxj, tradedcc, tradekcc, tradesxf));
		            	//设置邮箱为已读
		            	message[i].setFlag(Flags.Flag.SEEN, true);
		            	//更改一下 更改一下数据库时间
		            	
		            	
		            	//insert(re.getFrom(),re.getSentDate(),re.getSubject(),re.getBodyText(),tradetime, tradedn, tradegskCount, tradetradeCount, tradeclName, tradecsID, tradecontract, trade1, trade2, trade3, trade4, trade5, trade6, trade7, trade8);
		            
		            }
	            }
	             
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
