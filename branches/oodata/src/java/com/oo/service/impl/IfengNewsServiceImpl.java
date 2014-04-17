package com.oo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oo.dao.BaseDAO;
import com.oo.model.IfengNews;
import com.oo.service.IfengNewsService;

@Service("ifengNewsService")
public class IfengNewsServiceImpl implements IfengNewsService {
	
	@Autowired
	private BaseDAO<IfengNews> baseDAO;
	
	public List<IfengNews> getMessage(Map<String,String> params){
		return baseDAO.findByQuery("ifengNews.queryIfengNewsByMap", params);
	}
	
}
