package com.oo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oo.dao.BaseDAO;
import com.oo.model.Ahref;
import com.oo.service.ParserAherfService;

@Service("parserAherfService")
public class ParserAherfServiceImpl implements ParserAherfService {
	
	@Autowired
	private BaseDAO baseDAO;
	
	public Integer insertAherf(Ahref ahref){
		return baseDAO.create("ahref.insert", ahref);
	}
	
}
