package com.machine.mybatis.servive.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.machine.mybatis.dao.IDeptDAO;
import com.machine.mybatis.servive.IDeptService;
import com.machine.mybatis.vo.Dept;

/**
 * @Description: DeptServiceImpl
 *
 * @author machine
 * @date 2017年12月18日 上午12:14:35
*/
@Service
public class DeptServiceImpl implements IDeptService {
	
	@Resource
	private IDeptDAO deptDAO;

	@Override
	@Transactional(readOnly=true)
	public List<Dept> list() {
		return this.deptDAO.findAll();
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean add(Dept dept) {
		return this.deptDAO.doCreate(dept);
	}
}
