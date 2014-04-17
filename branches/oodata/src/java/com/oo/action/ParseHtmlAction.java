package com.oo.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class ParseHtmlAction extends BaseAction {

	@RequestMapping(value = "/getUrl.htm", method = RequestMethod.POST)
	@ResponseBody
	public String parseHTML(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String url = request.getParameter("url");
		String pageNo = request.getParameter("pageNo");
		String regex = request.getParameter("pattern");
		
		Connection connection = Jsoup.connect(url);
		Connection.Response resp = connection
				.userAgent(
						"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
				.timeout(10000).execute();

		int statusCode = resp.statusCode();
		if (statusCode == 200) {
			Document doc = connection.get();
			System.out.println(doc);
			Elements element = doc.select("loc");
			for (Element urls : element) {
				System.out.println(urls.text());
			}
		} else {
			System.out.println("received error code : " + statusCode);
		}
		
		return regex;
	}
}
