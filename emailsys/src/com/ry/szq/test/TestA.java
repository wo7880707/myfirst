package com.ry.szq.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ry.szq.common.ShowMail;
import com.ry.szq.pojo.Email;
import com.ry.szq.pojo.EmailDate;
import com.ry.szq.service.EmailDateService;
import com.ry.szq.service.EmailService;

public class TestA {
	@Test
	public void a(){
		try {
			ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
			//得到添加的最后时间
			EmailDateService ed = (EmailDateService)ac.getBean("emailDateService");
			EmailService es = (EmailService)ac.getBean("emailService");
			String str = ed.query().getDate();
			SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss"); 
			Date date = format.parse(str);
			//最后存储的时间Long值
			long endTime = date.getTime();
			//网站协议名 账号和密码
	        String host = "pop.163.com";  
	        String username = "songzhiqiang601@163.com";  
	        String password = "wo7880707";  
	        //获得mailSession
	        Properties props = new Properties();  
	        Session session = Session.getDefaultInstance(props, null);  
	        //链接pop3协议
	        Store store = session.getStore("pop3");  
	        store.connect(host, username, password);  
	        //连接收件箱
	        Folder folder = store.getFolder("INBOX");  
	        folder.open(Folder.READ_ONLY);  
	        //获得邮件
	        Message message[] = folder.getMessages();  
	        System.out.println("邮件数量:　" + message.length);  
	        ShowMail re = (ShowMail)ac.getBean("showMail");
	  
	        for (int i = 0; i < message.length; i++) {  
	            re = new ShowMail((MimeMessage) message[i]);  
	           /* System.out.println("邮件　" + i + "　主题:　" + re.getSubject());  
	            System.out.println("邮件　" + i + "　发送时间:　" + re.getSentDate());  
	            System.out.println("邮件　" + i + "　是否需要回复:　" + re.getReplySign());  
	            System.out.println("邮件　" + i + "　是否已读:　" + re.isNew());  
	            System.out.println("邮件　" + i + "　是否包含附件:　"  + re.isContainAttach((Part) message[i]));  
	            System.out.println("邮件　" + i + "　发送人地址:　" + re.getFrom());  
	            System.out.println("邮件　" + i + "　收信人地址:　" + re.getMailAddress("to"));  
	            System.out.println("邮件　" + i + "　抄送:　" + re.getMailAddress("cc"));  
	            System.out.println("邮件　" + i + "　暗抄:　" + re.getMailAddress("bcc"));  
	            re.setDateFormat("yy年MM月dd日　HH:mm");  
	            System.out.println("邮件　" + i + "　发送时间:　" + re.getSentDate());  
	            System.out.println("邮件　" + i + "　邮件ID:　" + re.getMessageId());  
	            re.getMailContent((Part) message[i]);  
	            System.out.println("邮件　" + i + "　正文内容:　\r\n" + re.getBodyText());*/ 
	            //邮箱的发送时间
	            Date time = re.getDate();
	            //如果邮件接收时间大于数据库读取的时间  就把信息存储到数据库 
	            if(time.getTime() > endTime){
	            	//设置本地位置
	            	re.setAttachPath("D:\\SZQ\\附件");  
	            	//保存附件 并 存到数据库里
		            if(re.isContainAttach((Part) message[i]) == false){
		            	es.insert(new Email(re.getFrom(),re.getSentDate(),re.getSubject(),re.getBodyText()," ",""));
		            	//更改一天 更改一下数据库时间
		            	ed.update(new EmailDate(1,re.getSentDate()));
		            }else {
		            	re.saveAttachMent((Part) message[i]); 
		            	es.insert(new Email(re.getFrom(),re.getSentDate(),re.getSubject(),re.getBodyText(),"D:\\SZQ\\附件\\" + re.getName((Part) message[i]),""));
		            	//更改一天 更改一下数据库时间
		            	ed.update(new EmailDate(1,re.getSentDate()));
		            }
	            }
	            //设置附件下载地点 把形参值传递给属性saveAttachPath   
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
}

