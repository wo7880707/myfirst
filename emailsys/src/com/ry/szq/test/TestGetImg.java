package com.ry.szq.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

import sun.misc.BASE64Encoder;

public class TestGetImg {
	public static void main(String[] args) {
	
		/*FileInputStream fis = new FileInputStream(new File("D:/imgtest/timg.jpg));
        FileOutputStream fos = new FileOutputStream(new File("D:/imgtest/timg2.jpg));
 
        byte[] read = new byte[1024];
        int len = 0;
        while((len = fis.read(read))!= -1){
            fos.write(read,0,len);
        }
        System.out.println("--------" + new String(read));
        fis.close();
        fos.flush();
        fos.close();*/
		//img转成字符串
		/*String imgFile = "D:/imgtest/timg.jpg";
		FileInputStream in = null;
		byte[] data = null;
 
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
 
		// 返回Base64编码过的字节数组字符串
		System.out.println(encoder.encode(data));
		System.out.println(new String(data));
		 */		
		//取目录中取到所有文件
		String[] all = new String[5];
		File file = new File("D:/imgtest");
		File[] imgs =  file.listFiles();
		/*for (int i = 0; i < imgs.length; i++) {
			System.out.println(imgs[i].getName());
		}*/
		for (int i = 0; i < imgs.length; i++) {
			all[i] = TestGetImg.imgToString("D:/imgtest/" + imgs[i].getName());
		}
		for (int j = 0; j < all.length; j++) {
			System.out.println(imgs[j] + "--------------------" + all[j]);
		}
		
		
	}
	public static String imgToString (String imgName) {
		FileInputStream in = null;
		byte[] data = null;
 
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgName);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}
}
