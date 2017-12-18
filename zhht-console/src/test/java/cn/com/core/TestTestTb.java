package cn.com.core;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.core.bean.TestTb;
import cn.com.core.service.TestTbService;

/**
 * Junit单元测试
 * @author lx
 *
 */

public class TestTestTb extends SpringJunitTest {

	@Autowired
	private TestTbService testTbService;
	
	@Test
	public void testAdd() throws Exception {
		TestTb testTb = new TestTb();
		testTb.setName("成功入库666");
	    testTb.setBirthday(new Date());
	    testTbService.addTestTb(testTb);
	}
}

