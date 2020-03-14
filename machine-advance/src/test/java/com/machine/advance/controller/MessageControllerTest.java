package com.machine.advance.controller;

import com.machine.advance.AdvanceMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import junit.framework.TestCase;
import javax.annotation.Resource;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = AdvanceMain.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class MessageControllerTest {
	@Resource
	private MessageController messageController;

	/**
	 * @Description: Browser[http://localhost:8080/advance/message/hello]
	 *
	 * @author machine
	 * @date 2017年12月17日 上午11:26:12
	 */
	@Test
	public void testHello() {
		TestCase.assertEquals(this.messageController.hello(), "欢迎machine来到世界");
	}

	/**
	 * @Description: Browser[http://localhost:8080/message/echo?msg=mahine]
	 *
	 * @author machine
	 * @date 2017年12月17日 上午11:26:12
	 */
	@Test
	public void testEcho() {
		TestCase.assertEquals(this.messageController.echo("machine"), "欢迎machine来到世界");
	}

	/**
	 * @Description: Browser[http://localhost:8080/message/messageService]
	 *
	 * @author machine
	 * @date 2017年12月17日 上午11:44:14
	 */
	@Test
	public void testMessageInfo() {
		TestCase.assertEquals(this.messageController.messageService(), "www.machine.com");
	}

	/**
	 * @Description: Browser[http://localhost:8080/message/configMessageService]
	 *
	 * @author machine
	 * @date 2017年12月17日 上午11:44:14
	 */
	@Test
	public void testConfigMessageService() {
		TestCase.assertEquals(this.messageController.configMessageService(), "www.machine.com");
	}
}
