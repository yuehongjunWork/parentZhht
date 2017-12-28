package cn.com.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;




import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.common.fdfs.FastDFSUtils;
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
		testTb.setName("成功入库886");
	    testTb.setBirthday(new Date());
	    testTbService.addTestTb(testTb);
	}
	
	
}

