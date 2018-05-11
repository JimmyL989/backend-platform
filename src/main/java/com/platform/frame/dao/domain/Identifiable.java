package com.platform.frame.dao.domain;

import java.io.Serializable;

/**
 * 主键标识
 * 
 */
public interface Identifiable extends Serializable {
	/**
	 * 获取主键
	 * 
	 * @return
	 */
	public Integer getId();

	/**
	 * 设置ID属性
	 * @param id
	 */
	public void setId(Integer id);
}
