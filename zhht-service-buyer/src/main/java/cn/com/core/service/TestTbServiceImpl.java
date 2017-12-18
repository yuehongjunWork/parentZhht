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
	
	/*@Autowired
	private CarTbService carTbService;
	
	@Autowired
	private MoneyTbService moneyTbService;*/
	
	/*@Autowired
	@Qualifier("carTbService")
	private CarTbService carTbService;
	
	@Autowired
	@Qualifier("moneyTbService")
	private MoneyTbService moneyTbService;*/
	/*private CarTbServiceImpl carTbService = new CarTbServiceImpl();
	
	private MoneyTbServiceImpl moneyTbService = new MoneyTbServiceImpl();*/
	
	
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void addTestTb(TestTb testTb){
		try{			
			Date currentDate = new Date();
			System.out.println("TestTbService1" + currentDate);
			System.out.println("1==========="+testTb.toString());
			insertTestTb2(testTb);
			/*System.out.println("2===========");
			CarTb carTb = new CarTb(testTb.getName(),testTb.getPassword());
			System.out.println("3==========="+carTb.toString());
			carTbService.insertCarTb(carTb);
			MoneyTb moneyTb = new MoneyTb(testTb.getName(),testTb.getPassword());
			moneyTb = null;
			System.out.println("4==========="+moneyTb.toString());
			moneyTbService.insertMoneyTb(moneyTb);*/
			
//			throw new RuntimeException();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
		
	public void insertTestTb2(TestTb testTb){
		try{			
			Date data = new Date();
			System.out.println("TestTbService2" + data);
			//testTb=null;
			/*testTb.setName("2name");
			testTb.setPassword("2password");*/
			testTbDao.addTestTb(testTb);			
		}catch(Exception e){
			throw new RuntimeException();
		}
	}
	
}
