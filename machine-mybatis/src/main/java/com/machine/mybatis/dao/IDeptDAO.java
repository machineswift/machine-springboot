package com.machine.mybatis.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.machine.mybatis.vo.Dept;

/**
 * @Description: IDeptDAO
 *
 * @author machine
 * @date 2017年12月18日 上午12:12:46
*/
@Mapper
public interface IDeptDAO {
	
	public List<Dept>findAll();
	
	public boolean doCreate(Dept dept);
}
