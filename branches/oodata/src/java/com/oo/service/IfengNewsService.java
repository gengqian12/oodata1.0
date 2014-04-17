package com.oo.service;

import java.util.List;
import java.util.Map;

import com.oo.model.IfengNews;


public interface IfengNewsService {
	
	public List<IfengNews> getMessage(Map<String,String> params);

}
