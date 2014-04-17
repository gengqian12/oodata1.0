package com.oo.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class FileUtil {
	
	//保存图片  
	public static void savePicFile(String url, String filename ){  
	    BufferedInputStream in;  
	    BufferedOutputStream out;  
	    URL u;  
	    try {  
	        //save picture  
	         u = new URL(url);  
	        URLConnection uc = u.openConnection();  
	        in = new BufferedInputStream(uc.getInputStream());  
	        out = new BufferedOutputStream(new FileOutputStream(filename));  
	          
	        int i;  
	        while (( i = in.read()) != -1){  
	            out.write(i);  
	        }  
	        out.flush();  
	  
	        out.close();  
	        in.close();  
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	        System.out.println("save picure failture!");  
	        return;  
	    }catch (IOException e) {  
	        e.printStackTrace();  
	        System.out.println("sava picture failture!");  
	        return;  
	    }   
	    System.out .println("save picture success!");  
	}  
	
	public static void main(String[] args) {
		FileUtil.savePicFile("http://img.blog.csdn.net/20130810164650421?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGludXh6YnE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast", "d:/a.jpg");
	}

}
