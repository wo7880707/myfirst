package com.ry.szq.listener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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

public class EmailRevice implements ServletContextListener{
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	public void contextInitialized(ServletContextEvent event){
		ServletContext ct = event.getServletContext();
		Flush flush = new Flush();
		flush.setCt(ct);
		Thread thread = new Thread(flush);
		thread.start();
		
	}
	class Flush implements Runnable{
		private ServletContext ct;	
		public void setCt(ServletContext ct) {
			this.ct = ct;
		}
		public void run() {
			while(true){ 
				try {  
	                Thread.sleep(60*1000*50);  //   让当前线程休眠1min 
	            } catch (InterruptedException e) {  
	                // TODO: handle exception  
	                e.printStackTrace();  
	            } 
				try {
					ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
					//得到添加的最后时间
					EmailDateService ed = (EmailDateService)ac.getBean("emailDateService");
					EmailService es = (EmailService)ac.getBean("emailService");
					TradeService ts = (TradeService)ac.getBean("tradeService");
					String str = ed.query().getDate();
					System.out.println("--------------时间---------------");
					System.out.println("时间" + str);
					SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss"); 
					Date date = format.parse(str);
					//最后存储的时间Long值
					long endTime = date.getTime();
					System.out.println("时间long:" + endTime);
					
					//网站协议名 账号和密码
					//imap连接
			        /*String user = "songzhiqiang601@163.com";// 邮箱的用户名   
			        String password = "wo7880707"; // 邮箱的密码   
			   
			        Properties prop = System.getProperties();   
			        prop.put("mail.store.protocol", "imap");   
			        prop.put("mail.imap.host", "imap.163.com");*/   
					String user = "kairui_test@163.com";// 邮箱的用户名   
			        String password = "RuiYingkeji456"; // 邮箱的密码   
			   
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
			        	//System.out.println("-----------for循环进来了------");
			            re = new ShowMail((MimeMessage) message[i]); 
			            //邮箱的发送时间
			            Date time = re.getDate();
			            //System.out.println("-----------邮箱的发送时间------" + time.getTime());
			            //就把信息存储到数据库   条件：1.如果邮件接收时间大于数据库读取的时间 2.有附件 
			            if(time.getTime() > endTime && re.getFrom().equals("NoGskAcc<kairui_test@163.com>")){
			            	System.out.println("-----------对比时间进来了------");
			            	//设置本地位置 不存储在本地
			            	re.setAttachPath("");  
			            	//判断是否有附件 保存附件 并 存到数据库里
				            if(re.isContainAttach((Part) message[i]) == false){
				            	//获得正文StringBuffer
				            	re.getMailContent((Part) message[i]); 
				            	//插入数据库
				            	/*//去掉<>的正则 不然在table中不显示数据
				            	String regex = "[<>]";*/
				            	Email email = new Email(re.getFrom().replace("<", "").replace(">", "").trim(),re.getSentDate(),re.getSubject(),re.getBodyText().trim(),"","");
				            	es.insert(email);
				            	//更改一下 更改一下数据库时间
				            	ed.update(new EmailDate(1,re.getSentDate()));
				            	//同时插入trade
				            	//先得到原始数据的Id
				            	//id作为主键插入到trade中 还要找出其他的属性字段
				            	int id = email.getId();
				            	System.out.println("----------------------------");
				            	System.out.println(id);
				            	//时间
				            	String tradetime = re.getSubject().split("_")[0];
				            	System.out.println(tradetime);
				            	//白夜
				            	String tradedn = re.getSubject().split("_")[1];
				            	System.out.println(tradedn);
				            	//杠上开账户
				            	String tradegskCount = re.getSubject().split("_")[2];
				            	System.out.println(tradegskCount);
				            	//交易账户
				            	String tradetradeCount = re.getSubject().split("_")[3];
				            	System.out.println(tradetradeCount);
				            	//策略名
				            	String tradeclName = re.getBodyText().split("]")[0].trim().replace("[", "");
				            	System.out.println(tradeclName);
				            	//ID
				            	String tradecsID = re.getBodyText().split("]")[1].split("<")[1].split(">")[0];
				            	System.out.println(tradecsID);
				            	//合约
				            	String tradecontract = re.getBodyText().split("]")[1].split("<")[2].split(">")[0];
				            	System.out.println(tradecontract);
				            	//汉字的正则
				            	String regex1 = "[\\u4e00-\\u9fa5]";
				        		//String shuzi = str.replaceAll(regex1, "");
				            	//期望收益 : 529.72
				            	String trade1 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[1];
				            	System.out.println(trade1);
				            	String tradeqwsy = trade1.replaceAll(regex1, "").trim();
				            	System.out.println(tradeqwsy);
				            	//平仓盈亏 : 650.00
				            	String trade2 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[2];
				            	System.out.println(trade2);
				            	String tradepcyk = trade2.replaceAll(regex1, "").trim();
				            	System.out.println(tradepcyk);
				            	//持仓盈亏 : 0.00
				            	String trade3 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[3];
				            	System.out.println(trade3);
				            	String tradeccyk = trade3.replaceAll(regex1, "").trim();
				            	System.out.println(tradeccyk);
				            	//浮动收益 : 620.28
				            	String trade4 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[4];
				            	System.out.println(trade4);
				            	String tradefdsy = trade4.replaceAll(regex1, "").trim();
				            	System.out.println(tradefdsy);
				            	//最新价   : 7917.00
				            	String trade5 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[5];
				            	System.out.println(trade5);
				            	String tradezxj = trade5.replaceAll(regex1, "").trim();
				            	System.out.println(tradezxj);
				            	//多持仓   : 0
				            	String trade6 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[6];
				            	System.out.println(trade6);
				            	String tradedcc = trade6.replaceAll(regex1, "").trim();
				            	System.out.println(tradedcc);
				            	//空持仓   : 0
				            	String trade7 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[7];
				            	System.out.println(trade7);
				            	String tradekcc = trade7.replaceAll(regex1, "").trim();
				            	System.out.println(tradekcc);
				            	//手续费   : 29.72
				            	String trade8 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[8];
				            	System.out.println(trade8);
				            	String tradesxf = trade8.replaceAll(regex1, "").trim();
				            	System.out.println(tradesxf);
				            	System.out.println("----------------------------");
				            	System.out.println("----------------------------");
				            	//插入数据
				            	ts.insert(new Trade(id,tradetime, tradedn, tradegskCount, tradetradeCount, tradeclName, tradecsID, tradecontract, tradeqwsy, tradepcyk, tradeccyk, tradefdsy, tradezxj, tradedcc, tradekcc, tradesxf));
				            	//设置邮箱为已读
				            	message[i].setFlag(Flags.Flag.SEEN, true);
				            }else {
				            	System.out.println("---------进入有附件的地方-------------------");
				            	//获得正文StringBuffer
				            	re.getMailContent((Part) message[i]); 
				            	//保存附件的base64 到数据库
				            	String base64 = re.saveAttachMent((Part) message[i]);
				            	//插入数据库
				            	String regex = "[<>]";
				            	System.out.println("马上是邮件存储");
				            	Email email = new Email(re.getFrom().replace("<", "").replace(">", "").trim(),re.getSentDate(),re.getSubject(),re.getBodyText().trim(),re.getName((Part) message[i]),base64);
				            	es.insert(email);
				            	//更改一下 更改一下数据库时间
				            	ed.update(new EmailDate(1,re.getSentDate()));
				            	//同时插入trade
				            	//先得到原始数据的Id
				            	//id作为主键插入到trade中 还要找出其他的属性字段
				            	int id = email.getId();
				            	System.out.println("----------------------------");
				            	System.out.println(id);
				            	//时间
				            	String tradetime = re.getSubject().split("_")[0];
				            	System.out.println(tradetime);
				            	//白夜
				            	String tradedn = re.getSubject().split("_")[1];
				            	System.out.println(tradedn);
				            	//杠上开账户
				            	String tradegskCount = re.getSubject().split("_")[2];
				            	System.out.println(tradegskCount);
				            	//交易账户
				            	String tradetradeCount = re.getSubject().split("_")[3];
				            	System.out.println(tradetradeCount);
				            	//策略名
				            	String tradeclName = re.getBodyText().split("]")[0].trim().replace("[", "");
				            	System.out.println(tradeclName);
				            	//ID
				            	String tradecsID = re.getBodyText().split("]")[1].split("<")[1].split(">")[0];
				            	System.out.println(tradecsID);
				            	//合约
				            	String tradecontract = re.getBodyText().split("]")[1].split("<")[2].split(">")[0];
				            	System.out.println(tradecontract);
				            	//汉字的正则
				            	String regex1 = "[\\u4e00-\\u9fa5]";
				        		//String shuzi = str.replaceAll(regex1, "");
				            	//期望收益 : 529.72
				            	String trade1 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[1];
				            	System.out.println(trade1);
				            	String tradeqwsy = trade1.replaceAll(regex1, "").trim();
				            	System.out.println(tradeqwsy);
				            	//平仓盈亏 : 650.00
				            	String trade2 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[2];
				            	System.out.println(trade2);
				            	String tradepcyk = trade2.replaceAll(regex1, "").trim();
				            	System.out.println(tradepcyk);
				            	//持仓盈亏 : 0.00
				            	String trade3 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[3];
				            	System.out.println(trade3);
				            	String tradeccyk = trade3.replaceAll(regex1, "").trim();
				            	System.out.println(tradeccyk);
				            	//浮动收益 : 620.28
				            	String trade4 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[4];
				            	System.out.println(trade4);
				            	String tradefdsy = trade4.replaceAll(regex1, "").trim();
				            	System.out.println(tradefdsy);
				            	//最新价   : 7917.00
				            	String trade5 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[5];
				            	System.out.println(trade5);
				            	String tradezxj = trade5.replaceAll(regex1, "").trim();
				            	System.out.println(tradezxj);
				            	//多持仓   : 0
				            	String trade6 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[6];
				            	System.out.println(trade6);
				            	String tradedcc = trade6.replaceAll(regex1, "").trim();
				            	System.out.println(tradedcc);
				            	//空持仓   : 0
				            	String trade7 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[7];
				            	System.out.println(trade7);
				            	String tradekcc = trade7.replaceAll(regex1, "").trim();
				            	System.out.println(tradekcc);
				            	//手续费   : 29.72
				            	String trade8 = re.getBodyText().split("]")[1].split("<")[2].split(">")[1].split(":")[8];
				            	System.out.println(trade8);
				            	String tradesxf = trade8.replaceAll(regex1, "").trim();
				            	System.out.println(tradesxf);
				            	System.out.println("----------------------------");
				            	System.out.println("----------------------------");
				            	System.out.println(id + tradetime+ tradedn+ tradegskCount+ tradetradeCount+ tradeclName+ tradecsID+ tradecontract+ tradeqwsy+ tradepcyk+ tradeccyk+ tradefdsy+ tradezxj+ tradedcc+ tradekcc+ tradesxf);
				            	//插入数据
				            	ts.insert(new Trade(id,tradetime, tradedn, tradegskCount, tradetradeCount, tradeclName, tradecsID, tradecontract, tradeqwsy, tradepcyk, tradeccyk, tradefdsy, tradezxj, tradedcc, tradekcc, tradesxf));
				            	//设置邮箱为已读
				            	message[i].setFlag(Flags.Flag.SEEN, true);
				            	
				            }
			            }
			             
				}
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			
			
		}
		public void insert(){
			System.out.println("sas");
		}
				
	}

}
