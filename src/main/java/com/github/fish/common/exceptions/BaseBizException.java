package com.github.fish.common.exceptions;


import com.github.fish.common.enums.Module;

/**
 *  业务异常基类，所有业务异常都必须继承于此异常 定义异常时，需要先确定异常所属子系统模块。<br>
 * 	例如：用户管理添加设备报错 可以定义为 [20010001]<br>
 * 	前四位数为子系统（20）及模块编号（01），后4位为错误代码 ,惟一 <br>
 */

public class BaseBizException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Module module;

	/**
     * 异常信息
	 */
	private String msg;

	/**
	 * 具体异常码
	 */
	private Integer code;

	public BaseBizException(Integer code, String msg, Module module) {
		this.code = code;
		this.msg = msg;
		this.module = module;
	}

	public BaseBizException() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public Integer getCode() {
		return code;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public BaseBizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseBizException(Throwable cause) {
		super(cause);
	}

}