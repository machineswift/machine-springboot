package com.machine.mybatis.vo;

import java.io.Serializable;

/**
 * @Description: Dept
 *
 * @author machine
 * @date 2017年12月18日 上午12:14:44
*/
@SuppressWarnings("serial")
public class Dept implements Serializable{
	private Long no;
	private String name;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Dept [no=" + no + ", name=" + name + "]";
	}
}
