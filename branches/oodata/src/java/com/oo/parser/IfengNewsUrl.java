package com.oo.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection.Request;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class IfengNewsUrl {
	public static void main(String[] args) throws IOException {
		try {
//			org.jsoup.Connection.Request request = Jsoup.connect("http://search.ifeng.com/sofeng/search.action?q=%E9%99%86%E5%AE%B6%E5%98%B4%E6%96%B0%E9%97%BB&c=1&p=1").request().timeout(10);
			
			String url = "http://search.ifeng.com/sofeng/search.action?q=%E9%99%86%E5%AE%B6%E5%98%B4%E6%96%B0%E9%97%BB&c=1&p=";
			for(int i=1;i<50;i++){
				Document doc = Jsoup.connect(url+i).get();
				Elements links = doc.select("a");
				for (Element link : links) {
					String linkHref = link.attr("href"); 
					if(linkHref.contains("http://news.ifeng.com")||linkHref.contains("http://finance.ifeng.com")){
						System.out.println(linkHref);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
			
	
		}
	
//		http://haishangkangtingxkxc.soufun.com/bbs/1210231942~-1/485767780_485767780.htm
//		http://haishangkangtingxkxc.soufun.com/bbs/1210231942~-1/485767772_485767772.htm
//		http://haishangkangtingxkxc.soufun.com/bbs/1210231942~-1/485767855_485767855.htm
		

	public void getHref() throws IOException{
		Document doc = Jsoup.connect("http://haishangkangtingxkxc.soufun.com/bbs/").get();
		Elements links = doc.select("#ci0 a");
		for (Element link : links) {
			String linkHref = link.attr("href"); 
			if(linkHref.contains("http://haishangkangtingxkxc.soufun.com/bbs/121")){
				System.out.println(linkHref);
				Document doc1 = Jsoup.connect(linkHref).get();
//				Document doc1 = Jsoup.connect("http://haishangkangtingxkxc.soufun.com/bbs/1210231942~-1/485767780_485767780.htm").get();
				Element links1 = doc1.select("div.itcrcomment").first();
				System.out.println(links1);
			}
		}
			
	}
}
