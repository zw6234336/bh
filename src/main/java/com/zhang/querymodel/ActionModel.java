package com.zhang.querymodel;

import java.io.Serializable;

/**
 * action返回字段
 * 
 * @author zhangwei
 *
 * 2017年2月7日
 */
public class ActionModel implements Serializable {

	private static final long serialVersionUID = -113381346363367475L;

	/**
	 * 0 成功
	 * 1 失败
	 */
	private int code;

	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
