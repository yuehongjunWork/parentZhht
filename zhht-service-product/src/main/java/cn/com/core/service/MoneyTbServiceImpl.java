package cn.com.core.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.core.bean.MoneyTb;
import cn.com.core.bean.TestTb;
import cn.com.core.dao.MoneyTbDao;

@Service("moneyTbServiceLocal")
@Transactional
public class MoneyTbServiceImpl implements MoneyTbService{
	@Autowired
	private MoneyTbDao moneyTbDao;

	@Override
	public void insertMoneyTb(MoneyTb moneyTb) {
		try{			
			Date data = new Date();
			System.out.println("TestTbService2" + data);
			//testTb=null;
			/*testTb.setName("2name");
			testTb.setPassword("2password");*/
			moneyTbDao.insertMoneyTb(moneyTb);			
		}catch(Exception e){
			throw new RuntimeException();
		}
		
	}

	@Override
	public void insertMoneyTb2(MoneyTb moneyTb) {
		// TODO Auto-generated method stub
		
	}
	
}
