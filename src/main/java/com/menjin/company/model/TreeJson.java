package com.menjin.company.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Lin
 *
 *Easyui Tree 结构类
 * @param <T>
 */
public class TreeJson<T> {
	private static final long serialVersionUID = -3226875355919405201L;
	private int id;
	//显示名
	private String text;
	//状态：打开、关闭
	private String state;
	//图片
	private String iconCls;
	private boolean checked;
	private List<TreeJson<T>> children = new ArrayList<TreeJson<T>>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<TreeJson<T>> getChildren() {
		return children;
	}

	public void setChildren(List<TreeJson<T>> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "TreeJson [id=" + id + ", text=" + text + ", state=" + state
				+ ", iconCls=" + iconCls + ", checked=" + checked
				+ ", children=" + children + "]";
	}

}
