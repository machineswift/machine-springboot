package com.machine.advance.controller;

import javax.annotation.Resource;
import com.machine.advance.service.IMessageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: i18n(资源文件 )
 *
 * @author machine
 * @date 2017年12月17日 上午11:49:02
 */
@RestController
@RequestMapping("/message")
public class MessageController extends AbstractController {
	@Resource(name="messageServiceImpl")
	private IMessageService messageService;
	
	@Resource(name="configMessageServiceImpl")
	private IMessageService configMessageService;
	
	/**
	 * @Description: Browser[http://localhost:8080/message/hello]
	 *
	 * @author machine
	 * @date 2017年12月17日 上午11:26:12
	 */
	@RequestMapping("/hello")
	public String hello() {
		return super.getMessage("welcome。message", "machine");
	}

	/**
	 * @Description: Browser[http://localhost:8080/message/echo?msg=mahine]
	 *
	 * @author machine
	 * @date 2017年12月17日 上午11:44:14
	 */
	@RequestMapping("/echo")
	public String echo(String msg) {
		return super.getMessage("welcome。message", msg);
	}
	
	/**
	 * @Description: Browser[http://localhost:8080/message/messageService]
	 *
	 * @author machine
	 * @date 2017年12月17日 上午11:44:14
	 */
	@RequestMapping("/messageService")
	public String messageService() {
		return this.messageService.info();
	}	
	
	
	/**
	 * @Description: Browser[http://localhost:8080/message/configMessageService]
	 *
	 * @author machine
	 * @date 2017年12月17日 上午11:44:14
	 */
	@RequestMapping("/configMessageService")
	public String configMessageService() {
		return this.configMessageService.info();
	}	
}
