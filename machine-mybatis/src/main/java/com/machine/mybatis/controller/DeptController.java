package com.machine.mybatis.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.machine.mybatis.servive.IDeptService;

/**
 * @Description: DeptController
 *
 * @author machine
 * @date 2017年12月18日 上午12:13:22
*/
@RestController
@RequestMapping("/dept")
public class DeptController {
	@Resource
	IDeptService deptService;

	/**
	 * @Description: Browser[http://localhost:8080/dept/list] 
	 *
	 * @author machine
	 * @date 2017年12月18日 上午12:13:29
	*/
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list() {
		return this.deptService.list();
	}
}
