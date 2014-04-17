package com.oo.dao;

import java.util.List;

import com.oo.dao.model.Page;

public interface BaseDAO<E> {
	
	public int create(String stmtId, E pojo);

	public int update(String stmtId, E pojo);
	
	public int updatePojo(String stmtId, Object object);

	public int delete(String stmtId, E pojo);

	public <T> Page<T> findByQuery(String stmtId, final Page<T> page, Object... params);

	public <T> List<T> findByComposedQuery(String stmtExp, Object... params);

	public <T> List<T> findByQuery(String stmtId, Object... params);

	public <T> T findObjectByQuery(String stmtId, Object... params);


	
}