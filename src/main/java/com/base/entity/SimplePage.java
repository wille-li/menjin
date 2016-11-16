package com.base.entity;

import java.io.Serializable;

/**
 * 分页基类
 * @author wille
 *
 */
public class SimplePage implements Serializable {
	private static final long serialVersionUID = -6283758828350113183L;
	
	/**
	 *  默认页大小
	 */
	public static final int DEFAULT_COUNT = 20;
	
	/**
	 * 总条数
	 */
	protected int totalCount = 0;
	
	/**
	 * 每页大小
	 */
	protected int pageSize = DEFAULT_COUNT;
	
	/**
	 * 页码
	 */
	protected int pageNo = 1;
	
	/**
	 * 每页的第一条
	 */
	private int startRowNum;
	
	/**
	 * 每页最后一条
	 */
	private int endRowNum;

	public SimplePage() {
	}

	public SimplePage(int pageNo, int pageSize, int totalCount) {
		if (totalCount <= 0)
			this.totalCount = 0;
		else {
			this.totalCount = totalCount;
		}
		if (pageSize <= 0)
			this.pageSize = DEFAULT_COUNT;
		else {
			this.pageSize = pageSize;
		}
		if (pageNo <= 0)
			this.pageNo = 1;
		else {
			this.pageNo = pageNo;
		}
		if ((this.pageNo - 1) * this.pageSize >= totalCount)
			this.pageNo = (totalCount / this.pageSize);
	}

	public void adjustPage() {
		if (this.totalCount <= 0) {
			this.totalCount = 0;
		}
		if (this.pageSize <= 0) {
			this.pageSize = DEFAULT_COUNT;
		}
		if (this.pageNo <= 0) {
			this.pageNo = 1;
		}
		if ((this.pageNo - 1) * this.pageSize >= this.totalCount)
			this.pageNo = (this.totalCount / this.pageSize);
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public int getTotalPage() {
		int totalPage = this.totalCount / this.pageSize;
		if ((this.totalCount % this.pageSize != 0) || (totalPage == 0)) {
			++totalPage;
		}
		return totalPage;
	}

	public boolean isFirstPage() {
		return (this.pageNo <= 1);
	}

	public boolean isLastPage() {
		return (this.pageNo >= getTotalPage());
	}

	public int getNextPage() {
		if (isLastPage()) {
			return this.pageNo;
		}
		return (this.pageNo + 1);
	}

	public int getPrePage() {
		if (isFirstPage()) {
			return this.pageNo;
		}
		return (this.pageNo - 1);
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getStartRowNum() {
		if (isFirstPage()) {
			this.startRowNum = 0;
		} else {
			this.startRowNum = (this.pageNo - 1) * this.pageSize;
		}
		return this.startRowNum;
	}

	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}

	public int getEndRowNum() {
		this.endRowNum = (getStartRowNum() + this.pageSize + 1);
		return this.endRowNum;
	}

	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}
}
