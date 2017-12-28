package cn.com.core;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.core.bean.TestTb;
import cn.com.core.bean.product.Brand;
import cn.com.core.service.TestTbService;
import cn.com.core.service.product.BrandService;

/**
 * Junit单元测试
 * @author lx
 *
 */

public class TestTestTb extends SpringJunitTest {

	@Autowired
	private TestTbService testTbService;
	
	@Autowired
	private BrandService brandService;
	
	/*@Test
	public void testAdd() throws Exception {
		TestTb testTb = new TestTb();
		testTb.setName("成功入库t8888");
	    testTb.setBirthday(new Date());
	    testTbService.addTestTb(testTb);
	}*/
	
	@Test
	public void testBrandService() throws Exception {
		List<Brand> brands = brandService.selectBrandListByQuery(1);
		System.out.println("======"+brands.size());
	}
	
}

