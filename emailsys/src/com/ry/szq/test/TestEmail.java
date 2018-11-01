package com.ry.szq.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ry.szq.pojo.Email;
import com.ry.szq.service.EmailService;

import sun.misc.BASE64Decoder;

public class TestEmail {

	@Test
	public void test()throws Exception{
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmailService e = (EmailService)ac.getBean("emailService");
		//Email a = new Email("2221","","dasd","das","dadss","");
		/*System.out.println(e.insert(a));
		System.out.println(a.getId());*/
		Email a = e.selectByID(132);
		System.out.println(a.toString());
		/*String base64 = a.getFile();*/
		//base64.replaceAll(" ", "+");
		/*BASE64Decoder decoder = new BASE64Decoder();
		byte[] b;
		b = decoder.decodeBuffer(base64);*/

		/*for (int i = 0; i < b.length; i++) {
			System.out.println(b[i]);
		}*/
			
			/*System.out.println(str);*/


		//System.out.println(e.selectAll());
		//ShowMail e = (ShowMail)ac.getBean("showMail");
		//网站协议名 账号和密码
        /*String host = "pop.163.com";  
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
            System.out.println("邮件　" + i + "　主题:　" + re.getSubject());  
            System.out.println("邮件　" + i + "　发送时间:　" + re.getSentDate());  
            System.out.println("邮件　" + i + "　是否需要回复:　" + re.getReplySign());  
            System.out.println("邮件　" + i + "　是否已读:　" + re.isNew());  
            System.out.println("邮件　" + i + "　是否包含附件:　"  
                    + re.isContainAttach((Part) message[i]));  
            System.out.println("邮件　" + i + "　发送人地址:　" + re.getFrom());  
            //System.out.println("邮件　" + i + "　收信人地址:　" + re.getMailAddress("to"));  
           // System.out.println("邮件　" + i + "　抄送:　" + re.getMailAddress("cc"));  
           // System.out.println("邮件　" + i + "　暗抄:　" + re.getMailAddress("bcc"));  
            re.setDateFormat("yy年MM月dd日　HH:mm");  
            System.out.println("邮件　" + i + "　发送时间:　" + re.getSentDate());  
            System.out.println("邮件　" + i + "　邮件ID:　" + re.getMessageId());  
            re.getMailContent((Part) message[i]);  
            System.out.println("邮件　" + i + "　正文内容:　\r\n" + re.getBodyText());  
            //设置附件下载地点 把形参值传递给属性saveAttachPath
            re.setAttachPath("D:\\SZQ\\附件");  
            re.saveAttachMent((Part) message[i]);  
	}*/
}
}