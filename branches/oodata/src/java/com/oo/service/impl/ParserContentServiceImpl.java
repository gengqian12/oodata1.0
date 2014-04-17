package com.oo.service.impl;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oo.dao.BaseDAO;
import com.oo.service.ParserContentService;

@Service("parserContentService")
public class ParserContentServiceImpl implements ParserContentService {
	
	
	public void parseContend(String url,Map<String,String> paraMap,Map<String,String> resultMap){
		
//		try {
//			Document doc = Jsoup.connect(url).get();
//			String title = doc.title();
//			doc.getElementById(id)
//			resultMap.put("title", title);
//			for(Map.Entry<String, String> entry : paraMap.entrySet()){
//				Element element = doc.select(entry.getValue());
//				entry.getKey();
//			}
//			System.out.println(links1.text());
//		} catch (IOException e) {
//			
//		}
		
	}
	
	
}
