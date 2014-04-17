package com.oo.action;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class ParseContentAction extends BaseAction {
	
	/**
	 * 取得分页中所有的符合规则url
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/getContent.htm",method=RequestMethod.POST)
	@ResponseBody
	public String getContent(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		String url = request.getParameter("url");
		String pageNo = request.getParameter("pageNo");
		String regex = request.getParameter("pattern");
		if(StringUtils.isNotEmpty(pageNo)&&url.contains("[i]")){
			Integer no = Integer.valueOf(pageNo);
			for (int i=1;i<=no;i++) {
				url = url.replaceAll("\\[i\\]", i+"");
				Document doc = Jsoup.connect(url).get();
				Elements links = doc.select("a");
				for (Element link : links) {
					String linkHref = link.attr("href"); 
					if(StringUtils.isNotEmpty(regex)){
						Pattern pa = Pattern.compile(regex);
						Matcher m = pa.matcher(linkHref);
						if(m.find()){
							System.out.println(linkHref);//TODO
						}
					}
				}
			}
		}
		
		return getJson("ok");
	}
	
	
	public static void main(String[] args) {
		System.out.println("asdf[i]asidf".replaceAll("\\[i\\]", "1"));
	}

}
