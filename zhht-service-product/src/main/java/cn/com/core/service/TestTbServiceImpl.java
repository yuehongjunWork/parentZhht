package cn.com.core.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.core.bean.TestTb;
import cn.com.core.dao.TestTbDao;

@Service("testTbService")
@Transactional
public class TestTbServiceImpl implements TestTbService{
	@Autowired
	private TestTbDao testTbDao;
	
	public void addTestTb(TestTb testTb){
		try{			
			testTbDao.addTestTb(testTb);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
