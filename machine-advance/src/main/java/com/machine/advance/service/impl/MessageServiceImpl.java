package com.machine.advance.service.impl;

import org.springframework.stereotype.Service;
import com.machine.advance.service.IMessageService;

/**
 * @Description: MessageServiceImpl
 *
 * @author machine
 * @date 2017年12月17日 上午11:51:54
*/
@Service
public class MessageServiceImpl implements IMessageService {
	@Override
	public String info() {
		return "www.machine.com";
	}
}
