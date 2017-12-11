package cn.com.core;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import cn.com.core.bean.TestTb;
import cn.com.core.controller.SpringContextUtils;
import cn.com.core.service.TestTbService;

/*
 * 测试表
 * @author  yhj
 */
//依赖注解形式测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:springmvc-login.xml"})
public class TestTestTb {
	@Autowired
	@Qualifier("testTbService")
    private TestTbService testTbService;
	
	/*TestTbService testTbService = (TestTbService) SpringContextUtils.getBean("testTbService");
	private ClassPathXmlApplicationContext context;*/

	@Test
	public void testMq() {
		/*context = new ClassPathXmlApplicationContext(
                new String[]{"WEB-INF/classes/dubbo-consumer.xml"});
        context.start();
		TestTbService testTbService = (TestTbService) context
				.getBean("testTbService");*/
		TestTb testTb = new TestTb();
		testTb.setName("test");
		testTb.setPassword("123456");
		testTbService.insertTestTb(testTb);
	}
	
}