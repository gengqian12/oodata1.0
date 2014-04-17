package com.oo.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oo.model.IfengNews;
import com.oo.service.IfengNewsService;

@Controller
public class IfengNewsAction extends BaseAction {
	
	@Autowired
	IfengNewsService ifengNewsService;
	
	@RequestMapping(value="/queryIfengNews.shtml")
	@ResponseBody
	public String queryIfengNews(HttpServletRequest request,
			HttpServletResponse response){
		String keyWord = request.getParameter("keyWord");
		Map<String,String> params = new HashMap<String,String>();
		params.put("keyWord", keyWord);
		List<IfengNews> list = ifengNewsService.getMessage(params);
		return getJson(list);
	}

}
