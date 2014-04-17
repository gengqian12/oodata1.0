package com.oo.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oo.action.BaseAction;
import com.oo.model.Ahref;
import com.oo.service.ParserAherfService;

@Controller
public class ParserAhrefAction extends BaseAction {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	ParserAherfService parserAherfService;
	
	@RequestMapping(value="/parserHref.shtml")
	@ResponseBody
	public String parserHref(HttpServletRequest request,
			HttpServletResponse response){
		try {
//			org.jsoup.Connection.Request request = Jsoup.connect("http://search.ifeng.com/sofeng/search.action?q=%E9%99%86%E5%AE%B6%E5%98%B4%E6%96%B0%E9%97%BB&c=1&p=1").request().timeout(10);
			
			String url = "http://search.ifeng.com/sofeng/search.action?q=%E9%99%86%E5%AE%B6%E5%98%B4%E6%96%B0%E9%97%BB&c=1&p=";
			for(int i=1;i<350;i++){
				Document doc = Jsoup.connect(url+i).get();
				Elements links = doc.select("a");
				for (Element link : links) {
					String linkHref = link.attr("href"); 
					if(linkHref.contains("http://news.ifeng.com")||linkHref.contains("http://finance.ifeng.com")){
						System.out.println(linkHref);
						Ahref ahref = new Ahref();
						ahref.setStatus(0);
						ahref.setHref(linkHref);
						try {
							parserAherfService.insertAherf(ahref);
						}catch (Exception e) {
							
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}
	
	@RequestMapping(value="/parserContent.shtml")
	@ResponseBody
	public String parserContent(HttpServletRequest request,
			HttpServletResponse response){
		try {
			for(int i=1;i<350;i++){
				String url = null;
				Document doc = Jsoup.connect(url+i).get();
				Elements links = doc.select("a");
				for (Element link : links) {
					String linkHref = link.attr("href"); 
					if(linkHref.contains("http://news.ifeng.com")||linkHref.contains("http://finance.ifeng.com")){
						System.out.println(linkHref);
						Ahref ahref = new Ahref();
						ahref.setStatus(0);
						ahref.setHref(linkHref);
						try {
							parserAherfService.insertAherf(ahref);
						}catch (Exception e) {
							
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}
	
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("正则表达式");
		Matcher matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World");
		System.out.println(matcher.group(1));
	}

}
