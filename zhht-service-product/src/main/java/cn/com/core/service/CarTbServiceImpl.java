package cn.com.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.core.bean.CarTb;
import cn.com.core.dao.CarTbDao;

@Service("carTbServiceLocal")
@Transactional
public class CarTbServiceImpl implements CarTbService{
	@Autowired
	private CarTbDao carTbDao;

	@Override
	public void insertCarTb(CarTb carTb) {
		try{
			carTbDao.insertCarTb(carTb);
		}catch(Exception e){
			throw new RuntimeException();
		}
		
	}

	@Override
	public void insertCarTb2(CarTb carTb) {
		// TODO Auto-generated method stub
		
	}
	
}
