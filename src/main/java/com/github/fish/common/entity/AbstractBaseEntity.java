package com.github.fish.common.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


public class AbstractBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	/**
	 * 获取主键ID
	 *
	 * @return id - 主键ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置主键ID
	 *
	 * @param id 主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

}