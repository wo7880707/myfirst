package com.ry.szq.controller;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.szq.common.ShowMail;
import com.ry.szq.pojo.Email;
import com.ry.szq.service.EmailService;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;

import sun.misc.BASE64Decoder;


@Controller
@RequestMapping("/email")
public class EmailAction {
	@Autowired
	private EmailService emailService;
	@Autowired
	private ShowMail showMail;

	public ShowMail getShowMail() {
		return showMail;
	}

	public void setShowMail(ShowMail showMail) {
		this.showMail = showMail;
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	// 查找全部
	@RequestMapping("/showAll.action")
	public @ResponseBody ArrayList<Email> selectAll() {
		return emailService.selectAll();
	}

	// 模糊查找
	@RequestMapping("/showAllByCondition.action")
	public @ResponseBody ArrayList<Email> selectAllByCondition(@ModelAttribute Email email) {
		//System.out.println("-----------------------------");
		//System.out.println(email);
		//System.out.println("-----------------------------");
		return emailService.selectAllByCondition(email);
	}
	//不好使，存在文件里是base64的字符串
	//下载文件
	/*@RequestMapping("/download.action")
	public ResponseEntity<byte[]>  download(String id) throws IOException {
		
		Email email = emailService.selectByID(Integer.parseInt(id));
		//文件名字
		String fileName = email.getEmailFile();
		fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
		//base64字符串
		String base64 = email.getFile();
		BASE64Decoder encoder = new BASE64Decoder();
		byte[] b = encoder.decodeBuffer(base64);
		//建立一个File 把 byte[] 写里
		File file = new File(fileName);
		FileUtils.writeByteArrayToFile(file, b);
		HttpHeaders headers = new HttpHeaders(); 									     						
	headers.setContentDispositionFormData("attachment",fileName);//不自动打开   								
	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   //头文件内容类型								
	return new ResponseEntity<byte[]>(b,headers, HttpStatus.CREATED);	
	}*/
	//直接下载到指定地址的
	/*@RequestMapping("/download.action")
	public String download(String id ,HttpServletResponse response) throws IOException { 
		
		Email email = emailService.selectByID(Integer.parseInt(id));
		//文件名字
		String fileName = email.getEmailFile();
		//base64字符串
		String base64 = email.getFile();
		BASE64Decoder encoder = new BASE64Decoder();
		byte[] b = encoder.decodeBuffer(base64);
		//建立一个File 把 byte[] 写里
		File file = new File(id);
		FileUtils.writeByteArrayToFile(file, b);
		HttpHeaders headers = new HttpHeaders(); 									     						
	headers.setContentDispositionFormData("attachment",fileName);//不自动打开   								
	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   //头文件内容类型		
	BufferedOutputStream bos = null;  
    BufferedInputStream bis = null;  
    FileInputStream f = new FileInputStream(file);
    try {  
        bos = new BufferedOutputStream(new FileOutputStream("D:\\" + fileName));
        bis = new BufferedInputStream(f);  
        int c;  
        while ((c = bis.read()) != -1) {  
            bos.write(c);  
            bos.flush();  
        }  

    } catch (Exception exception) {  
        exception.printStackTrace();  
        //throw new Exception("文件保存失败!");  
    } finally {  
        bos.close();  
        bis.close();  
    }  
    return "redirect:/showallemail.html";
	}*/
	@RequestMapping("/download.action")
	public String download(String id ,HttpServletResponse response) throws IOException { 
		
		Email email = emailService.selectByID(Integer.parseInt(id));
		//文件名字
		String fileName = email.getEmailFile();
		//base64字符串
		String base64 = email.getFile();
		BASE64Decoder encoder = new BASE64Decoder();
		byte[] b = encoder.decodeBuffer(base64);
		//建立一个File 把 byte[] 写里
		File file = new File(id);
		FileUtils.writeByteArrayToFile(file, b);
		HttpHeaders headers = new HttpHeaders(); 									     						
	headers.setContentDispositionFormData("attachment",fileName);//不自动打开   								
	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   //头文件内容类型		
	//BufferedOutputStream bos = null;  
	OutputStream bos = null; 
    BufferedInputStream bis = null;  
    FileInputStream f = new FileInputStream(file);
    try {  
    	// 设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        bos = response.getOutputStream();
        bis = new BufferedInputStream(f);  
        int c;  
        while ((c = bis.read()) != -1) {  
            bos.write(c);  
            bos.flush();  
        }  

    } catch (Exception exception) {  
        exception.printStackTrace();  
        //throw new Exception("文件保存失败!");  
    } finally {  
        bos.close();  
        bis.close();  
    }  
    return "redirect:/showallemail.html";
	}
								


	// 删除客户端的邮件
	@RequestMapping("/deleteClientMails.action")
	public @ResponseBody int deleteClientMails(String startdate, String starttime, String enddate, String endtime) {
		System.out.println("-----------------" + startdate);
		System.out.println("-----------------" + starttime);
		System.out.println("-----------------" + enddate);
		System.out.println("-----------------" + endtime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 删除的开始时间
		StringBuilder start = new StringBuilder(startdate);
		start.append(" " + starttime + ":00");
		// 删除的结束时间
		StringBuilder end = new StringBuilder(enddate);
		end.append(" " + endtime + ":00");
		try {
			Date startTime = sdf.parse(start.toString());
			Date endTime = sdf.parse(end.toString());
			// 网站协议名 账号和密码
			// imap连接
			String user = "songzhiqiang601@163.com";// 邮箱的用户名
			String password = "wo7880707"; // 邮箱的密码

			Properties prop = System.getProperties();
			prop.put("mail.store.protocol", "imap");
			prop.put("mail.imap.host", "imap.163.com");
			
			Session session = Session.getInstance(prop);
			// http://config.mail.163.com/settings/imap/index.jsp?uid=songzhiqiang601@163.com
			int total = 0;
			// 使用imap会话机制，连接服务器
			IMAPStore store = (IMAPStore) session.getStore("imap");
			store.connect(user, password);
			IMAPFolder folder = (IMAPFolder) store.getFolder("INBOX"); // 收件箱
			folder.open(Folder.READ_WRITE);
			// 获取总邮件数
			total = folder.getMessageCount();
			System.out.println("-----------------共有邮件：" + total + " 封--------------");
			// 得到收件箱文件夹信息，获取邮件列表
			System.out.println("未读邮件数：" + folder.getUnreadMessageCount());
			Message[] message = folder.getMessages();
			System.out.println("-----------------邮件数量:　" + message.length + " 封--------------");
			// ShowMail re = (ShowMail)ac.getBean("showMail");
			ShowMail re = showMail;
			for (int i = 0; i < message.length; i++) {
				re = new ShowMail((MimeMessage) message[i]);
				// 邮箱的发送时间
				Date time = re.getDate();
				System.out.println("-----------------第：" + (i+1) + " 封--------------");
				System.out.println("-----------------邮件时间：" + time.getTime() + " 封--------------");
				System.out.println("-----------------开始：" + startTime.getTime() + " 封--------------");
				System.out.println("-----------------结束：" + endTime.getTime() + " 封--------------");
				// 刪除时间在这个范围内的
				if (time.getTime() > startTime.getTime() && time.getTime() < endTime.getTime()) {
				
				System.out.println("-----------------共有邮件：" + time.getTime() + " 封--------------");
				System.out.println("-----------------共有邮件：" + startTime.getTime() + " 封--------------");
				System.out.println("-----------------共有邮件：" + endTime.getTime() + " 封--------------");
					message[i].setFlag(Flags.Flag.DELETED, true);
				}
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;
	}

}
