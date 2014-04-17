package com.oo.dao.impl;

/**
 * 
 * 
 * DAO基础方法集：C.R.U.D.T.Q.S.
 * C is create
 * R is read
 * U is update
 * D is delete
 * T is count
 * Q is query
 * S is search
 * 
 * 子类应当尽可能重用上述方法
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.oo.dao.BaseDAO;
import com.oo.dao.model.Page;
import com.oo.dao.model.QueryResult;

public class BaseDAOImpl<E> extends SqlMapClientDaoSupport implements BaseDAO<E> {
	// 扩展ibatis的分页对象
	private SqlExecutor sqlExecutor;
	public static final int FIRST_PAGE_OFFSET = 0;
	public static final String COUNT_SUBFIX = "_COUNT";
	 
	public int create(String stmtId, E pojo) {
		Object id = getSqlMapClientTemplate().insert(stmtId, pojo);
		if (id == null) {
			return 0;
		} else {
			return (int) id;
		}
	}

	
	public int delete(String stmtId, E pojo) {
		return getSqlMapClientTemplate().delete(stmtId, pojo);
	}

	
	public int update(String stmtId, E pojo) {
		return getSqlMapClientTemplate().update(stmtId, pojo);
	}
	
	public int updatePojo(String stmtId, Object object) {
		return getSqlMapClientTemplate().update(stmtId, object);
	}

	
	public <T> List<T> findByComposedQuery(String stmtExp, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public <T> QueryResult<T> findByComposedQuery(String stmtExp, int offset, int size, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public <T> List<T> findByQuery(String stmtId, Object... params) {
		Object param = (params != null&&params.length>0) ? params[0] : null;
		try {
			if(param == null){
				return getSqlMapClientTemplate().queryForList(stmtId);
			}
			return getSqlMapClientTemplate().queryForList(stmtId, param);
		} catch (Exception e) {
			logger.error("查询错误 [语句编号=" + stmtId + ",查询参数=" + param + "]", e);
		}
		return null;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	
	public <T> QueryResult<T> findByQuery(String stmtId, int offset, int size, Object... params) {
		QueryResult<T> queryResult = new QueryResult<T>();
		
		Object param = (params != null&&params.length>0) ? params[0] : null;
		try {
			// 当offset值为FIRST_PAGE_OFFSET时，被默认为需要查询总数
			if (offset == FIRST_PAGE_OFFSET) {
				String countSqlId = stmtId + COUNT_SUBFIX;
				if (logger.isDebugEnabled()) {
					logger.debug("自动调用  [" + countSqlId + "]");
				}
				int totalSize = (Integer) this.getSqlMapClientTemplate().queryForObject(countSqlId, param);
				queryResult.setTotalCount(totalSize);
			}
			List list=getSqlMapClientTemplate().queryForList(stmtId, param, offset, size);
			queryResult.setResult(list);
		} catch (Exception e) {
			logger.error("查询错误 [语句编号=" + stmtId + ",查询参数=" + param + "]", e);
		}
		return queryResult;
	}

	
	public <T> Page<T> findByQuery(String stmtId, final Page<T> page, Object... params) {
		int offset = page.getFirst();
		int pageSize = page.getPageSize();
		QueryResult<T> temp = findByQuery(stmtId, offset, pageSize, params);
		page.setResult(temp.getResult());
		if (offset == FIRST_PAGE_OFFSET) {
			page.setTotalCount(temp.getTotalCount());
		}

		return page;
	}

	
	public <T> T findObjectByQuery(String stmtId, Object... params) {
		Object param = (params != null&&params.length>0) ? params[0] : null;
		try {
			return (T)getSqlMapClientTemplate().queryForObject(stmtId, param);
		} catch (Exception e) {
			logger.error("查询错误 [语句编号=" + stmtId + ",查询参数=" + param + "]", e);
		}
		return null;
	}

	
	public int executeSql(String stmtId, Object... params) {
		return 0;
	}

	
	public int executeComposedSql(String stmtExp, Object... params) {
		return 0;
	}

	public void initialize() throws Exception {
		if (sqlExecutor != null) {
			SqlMapClient sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
			// if (sqlMapClient instanceof ExtendedSqlMapClient) {
			//ReflectUtils.setFieldValue(((SqlMapClientImpl) sqlMapClient).getDelegate(), "sqlExecutor", SqlExecutor.class, sqlExecutor);
			// }
		}
	}

	public void setSqlExecutor(SqlExecutor sqlExecutor) {
		this.sqlExecutor = sqlExecutor;
	}
	/**
	 * Henry_Zeng 修改批量更新
	 */
	
	public <T> Integer updateBatch(final String stmtId, final List<T> paramList) {
		try {
			return (Integer) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					executor.startBatch();
					Integer count = 0 ;
					for (T param : paramList) {
						count = executor.update(stmtId, param);
					}
					executor.executeBatch();
					return count;
				}
			});
		} catch (Exception e) {
			logger.error("批量更新错误 [语句编号=" + stmtId + "]", e);
		}
		return null;
	}

	
	public <T> Integer deleteBatch(final String stmtId, final List<T> paramList) {
		try {
			return (Integer) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					executor.startBatch();
					Integer count = 0 ;
					for (T param : paramList) {
						count = executor.delete(stmtId, param);
					}
					executor.executeBatch();
					return count;
				}
			});
		} catch (Exception e) {
			logger.error("批量删除错误 [语句编号=" + stmtId + "]", e);
		}
		return null;
	}


}