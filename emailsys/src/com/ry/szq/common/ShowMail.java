package com.ry.szq.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.stereotype.Component;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;
import com.sun.mail.util.BASE64DecoderStream;

import sun.misc.BASE64Encoder;
@Component
public class ShowMail {
	//192.168.0.55 
	private MimeMessage mimeMessage = null;
	//附件下载后存放的目录
	private String saveAttachPath = "";
	// 存放邮件内容的StringBuffer对象 
	private StringBuffer bodyText = new StringBuffer(); 
	// 默认的日前显示格式
	//mimeMessage.getSentDate()得到的日期转换成自己要的这个下面的格式
	private String dateFormat = "yy-MM-dd HH:mm:ss";
	public ShowMail(){}
	public ShowMail(MimeMessage mimeMessage) {
		super();
		//创建一个
		this.mimeMessage = mimeMessage;
	} 
	/**  
     * 　*　获得发件人的地址和姓名 　  
     */  
    public String getFrom() throws Exception {  
        InternetAddress address[] = (InternetAddress[]) mimeMessage.getFrom();  
        String from = address[0].getAddress();  
        if (from == null) {  
            from = "";  
            System.out.println("无法知道发送者.");  
        }  
        String personal = address[0].getPersonal();  
  
        if (personal == null) {  
            personal = "";  
            System.out.println("无法知道发送者的姓名.");  
        }  
  
        String fromAddr = null;  
        if (personal != null || from != null) {  
            fromAddr = personal + "<" + from + ">";  
            System.out.println("发送者是：" + fromAddr);  
        } else {  
            System.out.println("无法获得发送者信息.");  
        }  
        return fromAddr;  
    }  
    /**  
     * 　*　获得邮件主题 　  
     */  
    public String getSubject() throws MessagingException {  
        String subject = "";  
        try {  
            System.out.println("转换前的subject：" + mimeMessage.getSubject());  
            subject = MimeUtility.decodeText(mimeMessage.getSubject());  
            System.out.println("转换后的subject: " + mimeMessage.getSubject());  
            if (subject == null) {  
                subject = "";  
            }  
        } catch (Exception exce) {  
            exce.printStackTrace();  
        }  
        return subject;  
    }  
    /**  
     * 　*　获得邮件发送日期 　DATE  
     */  
    public Date getDate() throws Exception {  
        Date sentDate = mimeMessage.getSentDate();  
        
        return sentDate;  
    }  
    /**  
     * 　*　获得邮件发送日期 　  
     */  
    public String getSentDate() throws Exception {  
        Date sentDate = mimeMessage.getSentDate();  
        System.out.println("发送日期 原始类型: " + dateFormat);  
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);  
        String strSentDate = format.format(sentDate);  
        System.out.println("发送日期 可读类型: " + strSentDate);  
        return strSentDate;  
    }  
    /**  
     * 　*　获得邮件正文内容 　  
     */  
    public String getBodyText() {  
    	String str = bodyText.toString();
        return str;  
    }  
    /**  
     * 　　*　解析邮件，把得到的邮件内容保存到一个StringBuffer对象中，解析邮件  
     * 　　*　主要是根据MimeType类型的不同执行不同的操作，一步一步的解析 　　  
     */  
  
    public void getMailContent(Part part) throws Exception {  
  
        String contentType = part.getContentType();  
        // 获得邮件的MimeType类型  
        System.out.println("邮件的MimeType类型: " + contentType);  
  
        int nameIndex = contentType.indexOf("name");  
  
        boolean conName = false;  
  
        if (nameIndex != -1) {  
            conName = true;  
        }  
  
        System.out.println("邮件内容的类型:　" + contentType);  
  
        if (part.isMimeType("text/plain") && conName == false) {  
            // text/plain 类型  
            bodyText.append((String) part.getContent());  
        } /*else if (part.isMimeType("text/html") && conName == false) {  
            // text/html 类型  
            bodyText.append((String) part.getContent());  
        }*/ else if (part.isMimeType("multipart/*")) {  
            // multipart/*  
            Multipart multipart = (Multipart) part.getContent();  
            int counts = multipart.getCount();  
            for (int i = 0; i < 1; i++) {  
                getMailContent(multipart.getBodyPart(i));  
            }  
        } else if (part.isMimeType("message/rfc822")) {  
            // message/rfc822  
            getMailContent((Part) part.getContent());  
        } else {  
  
        }  
    }  
    /**  
     * 　　*　判断此邮件是否需要回执，如果需要回执返回"true",否则返回"false" 　  
     */  
    public boolean getReplySign() throws MessagingException {  
  
        boolean replySign = false;  
  
        String needReply[] = mimeMessage  
                .getHeader("Disposition-Notification-To");  
  
        if (needReply != null) {  
            replySign = true;  
        }  
        if (replySign) {  
            System.out.println("该邮件需要回复");  
        } else {  
            System.out.println("该邮件不需要回复");  
        }  
        return replySign;  
    }  
    /**  
     *　获得此邮件的Message-ID 　　  
     */  
    public String getMessageId() throws MessagingException {  
        String messageID = mimeMessage.getMessageID();  
        System.out.println("邮件ID: " + messageID);  
        return messageID;  
    }  
    /**  
     * 判断此邮件是否已读，如果未读返回false,反之返回true  
     */  
    public boolean isNew() throws MessagingException {  
        boolean isNew = false;  
        Flags flags = ((Message) mimeMessage).getFlags();  
        Flags.Flag[] flag = flags.getSystemFlags();  
        System.out.println("flags的长度:　" + flag.length);  
        for (int i = 0; i < flag.length; i++) {  
            if (flag[i] == Flags.Flag.SEEN) {  
                isNew = true;  
                System.out.println("seen email...");  
                // break;  
            }  
        }  
        return isNew;  
    }  
    /**  
     * 判断此邮件是否包含附件  
     */  
    public boolean isContainAttach(Part part) throws Exception {  
        boolean attachFlag = false;  
        // String contentType = part.getContentType();  
        if (part.isMimeType("multipart/*")) {  
            Multipart mp = (Multipart) part.getContent();  
            for (int i = 0; i < mp.getCount(); i++) {  
                BodyPart mPart = mp.getBodyPart(i);  
                String disposition = mPart.getDisposition();  
                if ((disposition != null)  
                        && ((disposition.equals(Part.ATTACHMENT)) || (disposition  
                                .equals(Part.INLINE))))  
                    attachFlag = true;  
                else if (mPart.isMimeType("multipart/*")) {  
                    attachFlag = isContainAttach((Part) mPart);  
                } else {  
                    String conType = mPart.getContentType();  
  
                    if (conType.toLowerCase().indexOf("application") != -1)  
                        attachFlag = true;  
                    if (conType.toLowerCase().indexOf("name") != -1)  
                        attachFlag = true;  
                }  
            }  
        } else if (part.isMimeType("message/rfc822")) {  
            attachFlag = isContainAttach((Part) part.getContent());  
        }  
        return attachFlag;  
    }  
    /**  
     * 　*　获得附件名字 　  
     */ 
    public String getName(Part part) throws Exception {  
    	//全局的文件名字
        String fileName = "";  
        //判断message是不是multipart文件
        if (part.isMimeType("multipart/*")) {  
        	//如果是 就得到这个multipart对象
            Multipart mp = (Multipart) part.getContent();  
            //Message表示一个邮件，messgaes.getContent()返回一个Multipart对象。
            //一个Multipart对象包含一个或多个BodyPart对象，来组成邮件的正文部分（包括附件）。
            for (int i = 0; i < mp.getCount(); i++) {  
            	//遍历得到每个BodyPart对象
                BodyPart mPart = mp.getBodyPart(i);  
                String disposition = mPart.getDisposition();
                //文字头的值
                System.out.println("文字头的值 ："+ disposition);
                //个部分有内容的话  disposition 不为null
                if ((disposition != null)  
                        && ((disposition.equals(Part.ATTACHMENT)) || (disposition  
                                .equals(Part.INLINE)))) {  
                    fileName = mPart.getFileName();  
                    System.out.println("fileName1:::" + fileName);
                    fileName = MimeUtility.decodeText(fileName); 
                    if (fileName.toLowerCase().indexOf("gb2312") != -1) { 
                    	System.out.println("转码附件名");
                        fileName = MimeUtility.decodeText(fileName);  
                        
                    }  
                    System.out.println("fileName:::" + fileName);

                   // saveFile(fileName, mPart.getInputStream());  
                } else if (mPart.isMimeType("multipart/*")) {  
                   // saveAttachMent(mPart);  
                } else {  
                    fileName = mPart.getFileName();  
                    if ((fileName != null)  
                            && (fileName.toLowerCase().indexOf("GB2312") != -1)) {  
                        fileName = MimeUtility.decodeText(fileName);  
                      // saveFile(fileName, mPart.getInputStream());  
                    }  
                }  
            }  
        } else if (part.isMimeType("message/rfc822")) {  
           // saveAttachMent((Part) part.getContent());  
        }  
        return fileName;
    }
    /**  
     * 　*　保存附件 　  
     */  
    public String saveAttachMent(Part part) throws Exception {  
    	//全局的文件名字
    	String base64 = "";
        String fileName = "";  
        //判断message是不是multipart文件
        if (part.isMimeType("multipart/*")) {  
        	//如果是 就得到这个multipart对象
            Multipart mp = (Multipart) part.getContent();  
            //Message表示一个邮件，messgaes.getContent()返回一个Multipart对象。
            //一个Multipart对象包含一个或多个BodyPart对象，来组成邮件的正文部分（包括附件）。
            for (int i = 0; i < mp.getCount(); i++) {  
            	//遍历得到每个BodyPart对象
                BodyPart mPart = mp.getBodyPart(i);  
                String disposition = mPart.getDisposition();
                //文字头的值
                System.out.println("文字头的值 ："+ disposition);
                //个部分有内容的话  disposition 不为null
                if ((disposition != null)  
                        && ((disposition.equals(Part.ATTACHMENT)) || (disposition  
                                .equals(Part.INLINE)))) {  
                    fileName = mPart.getFileName();  
                    System.out.println("fileName1:::" + fileName);
                    fileName = MimeUtility.decodeText(fileName); 
                    if (fileName.toLowerCase().indexOf("gb2312") != -1) { 
                    	System.out.println("转码附件名");
                        fileName = MimeUtility.decodeText(fileName);  
                        
                    }  
                    System.out.println("fileName:::" + fileName);
                    base64 = saveFile(fileName, mPart.getInputStream());
                    //base64 = saveFile(fileName, mPart.getInputStream());  
                    //BASE64DecoderStream s = (BASE64DecoderStream) mPart.getInputStream();
                    //System.out.println(".................文件长度" +s.available());
                    //System.out.println("................." +s);
                    //System.out.println(".................流类型" +mPart.getInputStream());
                    //BufferedInputStream s2 = new BufferedInputStream(mPart.getInputStream());
                    //base64 = saveFile(fileName, s2); 
                   // s2.read();
                   // System.out.println(".................s2文件长度" +s2.available());
                } else if (mPart.isMimeType("multipart/*")) {  
                	 saveAttachMent(mPart);  
                } else {  
                    fileName = mPart.getFileName();  
                    if ((fileName != null)  
                            && (fileName.toLowerCase().indexOf("GB2312") != -1)) {  
                        fileName = MimeUtility.decodeText(fileName);  
                       // base64 = saveFile(fileName, mPart.getInputStream()); 
                        base64 =  saveFile(fileName, mPart.getInputStream());
                    }  
                }  
            }  
        } else if (part.isMimeType("message/rfc822")) {  
            saveAttachMent((Part) part.getContent());  
        }  
        return base64 ;
    }  
  
    /**  
     *　设置附件存放路径  
     */  
    public void setAttachPath(String attachPath) {  
        this.saveAttachPath = attachPath;  
    }  
  
    /**  
     * 　*　设置日期显示格式 　  
     */  
    public void setDateFormat(String format) throws Exception {  
        this.dateFormat = format;  
    }  
  
    /**  
     * 　*　获得附件存放路径 　  
     */  
    public String getAttachPath() {  
        return saveAttachPath;  
    }  
  
    /**  
     * 　*　真正的保存附件到指定目录里 　  
     */  
    private String saveFile(String fileName, InputStream in) throws Exception {  
    	//得到本机系统版本
    	String str = "";
        String osName = System.getProperty("os.name");  
        System.out.println(osName);
        //获得附件存放路径  自己设定
        String storeDir = getAttachPath();  
        System.out.println(storeDir);
        //设置 \\转义符号
        String separator = "";  
        if (osName == null) {  
            osName = "";  
        }  
        if (osName.toLowerCase().indexOf("win") != -1) {  
            separator = "\\";  
            if (storeDir == null || storeDir.equals(""))  
                storeDir = "c:\\tmp";  
        } else {  
            separator = "/";  
            storeDir = "/tmp";  
        }  
        System.out.println(storeDir );
        System.out.println(separator );
        System.out.println(fileName);
        File storeFile = new File(storeDir + separator + fileName); 
       // File storeFile = new File(storeDir + "\\gb18030BtP20psDtzsrM4i5kb2N4");
        System.out.println("附件的保存地址:　" + storeFile.toString());  
        //去重复文件的处理
        // for(int　i=0;storefile.exists();i++){  
        // storefile　=　new　File(storedir+separator+fileName+i);  
        // }  
    	// 将文件转化为字节数组字符串，并对其进行Base64编码处理
    	//InputStream in 是形参 
        BufferedInputStream bis = null;  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {  
           // bos = new BufferedOutputStream(new FileOutputStream(storeFile));  
            bis = new BufferedInputStream(in);  
            byte[] data = null; 
            data = new byte[1];
            System.out.println("---------------流的长度-" + bis.available());
            int count = -1;
            while((count = bis.read(data,0,1)) != -1){
            	outStream.write(data, 0, count);
            	outStream.flush();
            }
            BASE64Encoder encoder = new BASE64Encoder();  
            str = encoder.encode(outStream.toByteArray());
           //字符串要保存在数据库中
            
            //保存到本地
            /*int c;  
            while ((c = bis.read()) != -1) {  
                bos.write(c);  
                bos.flush();  
            }  */
        } catch (Exception exception) {  
            exception.printStackTrace();  
            throw new Exception("文件保存失败!");  
        } finally {  
            //bos.close();  
        	outStream.close();
            bis.close();  
        }  
        return str;
    }  
    /**  
     *　ReceiveEmail类测试  
     */  
    public static void main(String args[]) throws Exception {  
    	/*//pop3连接
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
  
        Folder folder = store.getFolder("INBOX");  
        folder.open(Folder.READ_ONLY);  
        //获得邮件
        Message message[] = folder.getMessages();  
        System.out.println("邮件数量:　" + message.length); */ 
    	//imap连接
    	 /*String user = "songzhiqiang601@163.com";// 邮箱的用户名   
         String password = "wo7880707"; // 邮箱的密码   
*/         
    	 String user = "kairui_test@163.com";// 邮箱的用户名   
	     String password = "RuiYingkeji456"; // 邮箱的密码   
         Properties prop = System.getProperties();   
         prop.put("mail.store.protocol", "imap");   
         prop.put("mail.imap.host", "imap.163.com");   
   
         Session session = Session.getInstance(prop);   
         //http://config.mail.163.com/settings/imap/index.jsp?uid=songzhiqiang601@163.com  
         // 使用imap会话机制，连接服务器
         IMAPStore store = (IMAPStore) session.getStore("imap"); 
         store.connect(user, password); 
         IMAPFolder folder = (IMAPFolder) store.getFolder("INBOX"); // 收件箱   
         folder.open(Folder.READ_WRITE);   
         // 获取总邮件数     
         System.out.println("-----------------共有邮件：" + folder.getMessageCount()   
                 + " 封--------------");   
         // 得到收件箱文件夹信息，获取邮件列表   
         System.out.println("未读邮件数：" + folder.getUnreadMessageCount());  
         System.out.println("新邮件数：" + folder.getNewMessageCount());
         Message[] message = folder.getMessages();   
        ShowMail re = null;  
       
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
            System.out.println("-----------------------------------　" );
            System.out.println("-----------------------------------　" );
            
            System.out.println("邮件　" + i + "　正文内容:　\r\n" + re.getBodyText());  
            System.out.println("-----------------------------------　");
            //设置附件下载地点 把形参值传递给属性saveAttachPath
            re.setAttachPath("");  
            re.saveAttachMent((Part) message[i]); 
            System.out.println("-----------------------------------　");
           // System.out.println("base64====:"  + base64 );
            System.out.println("-----------------------------------　");
            System.out.println("自己写的文件名字为：：：" + re.getName((Part) message[i]));
        }  
        //删除邮箱
       // message[3].setFlag(Flags.Flag.DELETED, true);
        //message[7].setFlag(Flags.Flag.DELETED, true);
        folder.close(true);
    }  
}
