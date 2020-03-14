package com.machine.advance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.machine.advance.service.impl.MessageServiceImpl;

/**
 * @Description: ServiceConfig
 *
 * @author machine
 * @date 2017年12月17日 下午12:10:49
*/
@Configuration
public class ServiceConfig {
	@Bean(name="configMessageServiceImpl")
	public MessageServiceImpl getMessageService() {
		return new MessageServiceImpl();
	}
}
