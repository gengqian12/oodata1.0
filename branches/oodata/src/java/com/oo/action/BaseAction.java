package com.oo.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;

public class BaseAction {

	public String getJson(Object obj){
		String json = JSON.toJSONString(obj);
		return json;
	}
	
	
}
