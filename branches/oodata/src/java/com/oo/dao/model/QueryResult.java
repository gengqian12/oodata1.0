package com.oo.dao.model;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class QueryResult<T> {
	protected List<T> result = null;
	protected int totalCount = 0;

	public QueryResult() {
	}

	/**
	 * 取得页内的记录列表.
	 */
	public List<T> getResult() {
		if (result == null)
			return Collections.emptyList();
		return result;
	}

	public void setResult(final List<T> result) {
		this.result = result;
	}

	/**
	 * 取得总记录数,默认值为-1.
	 */
	public int getTotalCount() {
		if(totalCount < 0)
			return 0;
		else
			return totalCount;
	}

	public void setTotalCount(final int totalCount) {
		this.totalCount = totalCount;
	}
	
	public void setTotalCount(final String totalCount) {
		if(StringUtils.isEmpty(totalCount) || "undefined".equalsIgnoreCase(totalCount))
			this.totalCount = 0;
		else
			this.totalCount = Integer.parseInt(totalCount);
	}
}
