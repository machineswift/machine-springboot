package com.machine.advance.controller;

import java.util.Locale;
import javax.annotation.Resource;
import org.springframework.context.MessageSource;

/**
 * @Description:公共抽象类 
 *
 * @author machine
 * @date 2017年12月17日 上午11:13:53
*/
public abstract class AbstractController {
	@Resource
	private MessageSource messsageSource;
	
	/**
	 * @Description: i18n
	 *
	 * @author machine
	 * @date 2017年12月17日 上午11:18:51
	*/
	public String getMessage(String key,String...args) {
		return this.messsageSource.getMessage(key, args, Locale.getDefault());
	}
}
