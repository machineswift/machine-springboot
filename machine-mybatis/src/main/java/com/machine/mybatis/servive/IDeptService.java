package com.machine.mybatis.servive;

import java.util.List;

import com.machine.mybatis.vo.Dept;

/**
 * @Description: IDeptService
 *
 * @author machine
 * @date 2017年12月18日 上午12:14:24
*/
public interface IDeptService {

	public List<Dept> list();

	boolean add(Dept dept);
}
