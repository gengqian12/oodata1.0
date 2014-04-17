package com.oo.action;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ParseUrlAction extends BaseAction {

	/**
	 * 取得分页中所有的符合规则url
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getUrl.htm", method = RequestMethod.POST)
	@ResponseBody
	public String getAgentUrl(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String url = request.getParameter("url");
		String pageNo = request.getParameter("pageNo");
		String regex = request.getParameter("pattern");
//		String regex = request.getParameter("pattern");
		if (StringUtils.isNotEmpty(pageNo) && url.contains("[i]")) {
			Integer no = Integer.valueOf(pageNo);
			for (int i = 1; i <= no; i++) {
				String url1 = url.replaceAll("\\[i\\]", i + "");
				System.out.println(url1);
//				Document doc = Jsoup.connect(url).get();
				Connection connection = Jsoup.connect(url1);
				Connection.Response resp = connection
						.userAgent(
								"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
						.timeout(10000).execute();
				int statusCode = resp.statusCode();
				if (statusCode == 200) {
					Document doc = connection.get();
//					Elements links = doc.select("li>a");
//					for (Element link : links) {
//						String linkHref = link.attr("href");
						if (StringUtils.isNotEmpty(regex)) {
							//<a title=\".*\" href=\"(.*.html)\" target=\"_blank\" name=\".*\">
							Pattern pa = Pattern.compile(regex);
							Matcher m = pa.matcher(doc.toString());
							while (m.find()) {
								System.out.println(m.group(1));// TODO
							}
						}
//					}
				} else {
					System.out.println("received error code : " + statusCode);
				}
				
			}
		} else {
			Document doc1 = Jsoup.connect(url).get();
			Elements links = doc1.select("a");
			for (Element link : links) {
				String linkHref = link.attr("href");
				if (StringUtils.isNotEmpty(regex)) {
					Pattern pa = Pattern.compile(regex);
					Matcher m = pa.matcher(linkHref);
					if (m.find()) {
						System.out.println(linkHref);// TODO
					}
				}
			}
		}

		return getJson("ok");
	}
	
	/**
	 * 取得分页中所有的符合规则url
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getNextAgentUrl.htm", method = RequestMethod.POST)
	@ResponseBody
	public String getNextAgentUrl(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String url = request.getParameter("url");
		String next = request.getParameter("next");
		String pageNo = request.getParameter("pageNo");
		String regex = request.getParameter("pattern");
		String domain = request.getParameter("domain");
		if (StringUtils.isNotEmpty(next)) {
			Integer no = 200;
			if (StringUtils.isNotEmpty(pageNo)){
				no = Integer.valueOf(pageNo);
			}
			while (StringUtils.isNotEmpty(url) || no>0) {
				--no;
				Document doc = null;
				String docString = null;
				Connection connection = Jsoup.connect(url);
				Connection.Response resp = connection
						.userAgent(
								"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
						.timeout(10000).execute();
				int statusCode = resp.statusCode();
				if (statusCode == 200) {
						doc = connection.get();
						docString = doc.toString();
						if (StringUtils.isNotEmpty(regex)) {
							Pattern pa = Pattern.compile(regex);
							Matcher m = pa.matcher(docString);
							while (m.find()) {
								System.out.println(m.group(1));// TODO
							}
						}
				} else {
					System.out.println("received error code : " + statusCode);
				}
			url = null;	
			Pattern pattern = Pattern.compile(next);
			Matcher m = pattern.matcher(docString);
			while (m.find()) {
				url = domain+URLDecoder.decode(m.group(1), "UTF-8");
				System.out.println(url);// TODO
			}
			}
		}

		return getJson("ok");
	}
	/**
	 * 取得分页中所有的符合规则url
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
//	@RequestMapping(value = "/getUrl.htm", method = RequestMethod.POST)
//	@ResponseBody
//	public String getUrl(HttpServletRequest request,
//			HttpServletResponse response) throws IOException {
//		String url = request.getParameter("url");
//		String pageNo = request.getParameter("pageNo");
//		String regex = request.getParameter("pattern");
//		if (StringUtils.isNotEmpty(pageNo) && url.contains("[i]")) {
//			Integer no = Integer.valueOf(pageNo);
//			for (int i = 1; i <= no; i++) {
//				String url1 = url.replaceAll("\\[i\\]", i + "");
//				System.out.println(url1);
//				Document doc = Jsoup.connect(url).get();
//				int statusCode = resp.statusCode();
//				if (statusCode == 200) {
//					Document doc = connection.get();
//					Elements links = doc.select("li>a");
//					for (Element link : links) {
//						String linkHref = link.attr("href");
//						if (StringUtils.isNotEmpty(regex)) {
//							Pattern pa = Pattern.compile(regex);
//							Matcher m = pa.matcher(linkHref);
//							if (m.find()) {
//								System.out.println(linkHref);// TODO
//							}
//						}
//					}
//				} else {
//					System.out.println("received error code : " + statusCode);
//				}
//				
//			}
//		} else {
//			Document doc1 = Jsoup.connect(url).get();
//			Elements links = doc1.select("a");
//			for (Element link : links) {
//				String linkHref = link.attr("href");
//				if (StringUtils.isNotEmpty(regex)) {
//					Pattern pa = Pattern.compile(regex);
//					Matcher m = pa.matcher(linkHref);
//					if (m.find()) {
//						System.out.println(linkHref);// TODO
//					}
//				}
//			}
//		}
//
//		return getJson("ok");
//	}
	
	/**
	 * 取得所有的url
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getAllUrl.htm", method = RequestMethod.POST)
	@ResponseBody
	public String getAllUrl(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String url = request.getParameter("url");
		String layer = request.getParameter("layer");// 层
		if (StringUtils.isNotEmpty(layer)) {
			Integer layer1 = Integer.valueOf(layer);
			Document doc = Jsoup.connect(url).get();
			Elements links = doc.select("a");
			for (Element link : links) {
				String linkHref = link.attr("href");
				System.out.println(linkHref);// TODO
			}
		}

		return getJson("ok");
	}

	private void bulitLink(String linkHref) {

	}

	public static void main(String[] args) throws IOException {
		// System.out.println("asdf[i]asidf".replaceAll("\\[i\\]", "1"));
		String url = "http://list.suning.com/%E5%B9%B3%E6%9D%BF%E7%94%B5%E8%A7%86/243505/cityId=9001&iy=0&st=14&si=5&cp=5#refresh";
//		url = "http://list.suning.com/";
//		 url = "http://shanghai.anjuke.com/sale/p2/#filtersort";
		// url="http://product.suning.com/103686453.html";
		// Document doc = Jsoup.connect(url).get();
		// // Document doc = Jsoup.parse(new URL(url), 10000);
		// System.out.println(doc);

		Connection connection = Jsoup.connect(url);
		Connection.Response response = connection
				.userAgent(
						"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
				.timeout(120000).execute();

		int statusCode = response.statusCode();
		if (statusCode == 200) {
			Document doc = connection.get();
//			System.out.println(doc);
//			Elements element = doc.select("loc");
//			for (Element urls : element) {
//				System.out.println(urls.text());
//			}
			
			String html = doc.toString();
			System.out.println(html);
//			String html = "<ul>   <br>  <li> <a title=\"0\" href=\"http://123.html\"></a>";
			//Pattern pa = Pattern.compile("<a title=\".*\" href=\"(.*.html)\" target=\"_blank\" name=\".*\">");
			Pattern pa = Pattern.compile("<a id=\"nextPage\" name=\".*\" class=\"next\" title=\"下一页\" href=\"(.*)\"><b></b>下一页</a>");
			//<a id="nextPage" name="ssdl_243505_bottom_pgdn02" class="next" title="下一页" href=""><b></b>下一页</a>
			Matcher m = pa.matcher(html);
			while (m.find()) {
				System.out.println("----------"+m.group(1));// TODO
			}
		} else {
			System.out.println("received error code : " + statusCode);
		}
	}

}
