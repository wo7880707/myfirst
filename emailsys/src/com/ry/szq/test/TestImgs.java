package com.ry.szq.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

public class TestImgs {
	public static void main(String[] args) {
		try {
	        String[] imgBase64 = imgsOpen("D:/imgtest/open");
	        if(imgBase64.length == 0){
	            System.out.println("没有文件");
	        } else {
	            for (int i = 0; i < imgBase64.length; i++) {
	               System.out.println("图片_" + i + "------" + imgBase64[i]); 
	            }
	        }
		}catch (NullPointerException e) {
			System.out.println("文件路径不正确");
		}
	        
	}
	public static String[] imgsOpen(String pathUrl) {
        String [] imgsNames = imgsToName(pathUrl);
        String[] imgsBase64 = new String[imgsNames.length];
        //找到目录下所有图片名
        for (int i = 0; i < imgsNames.length; i++) {
            imgsBase64[i] = imgToBase64(pathUrl + "/" + imgsNames[i]);
        }
        return imgsBase64;
    }
    /**
     * 图片——base64字符串
     */
    public static String imgToBase64(String imgUrl){
        FileInputStream in = null;
        byte[] data = null;

        // 读取图片字节数组
        try {
            in = new FileInputStream(imgUrl);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        return new BASE64Encoder().encode(data);
    }
    /**
     * 目录下所有的图片名字
     */
    public static String[] imgsToName(String pathUrl){
        //找到目录
        File file = new File(pathUrl);
        //目录下所有图片
        File[] imgsFiles =  file.listFiles();
        //名字数组
        String[] imgsNames = new String[imgsFiles.length];
        //找出名字
        for (int i = 0; i < imgsFiles.length; i++) {
            imgsNames[i] = imgsFiles[i].getName();
        }
        //排序 多个图片规则：a1 a2 a3
        Arrays.sort(imgsNames);
        return imgsNames;
    }
}
