package cn.com.core.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.core.bean.TestTb;
import cn.com.core.service.TestTbService;

/**
 * 后台管理
 * @author lx
 *
 */
@Controller
@RequestMapping(value = "/control")
public class CenterController {

	
	//入口
		
	@RequestMapping(value = "/index.do")
	public String index(){
		
		return "index";
	}
	//头
	@RequestMapping(value = "/top.do")
	public String top(){
		return "top";
	}
	//身体
	@RequestMapping(value = "/main.do")
	public String main(){
		return "main";
	}
	//左
	@RequestMapping(value = "/left.do")
	public String left(){
		return "left";
	}
	//右
	@RequestMapping(value = "/right.do")
	public String right(){
		return "right";
	}
	//商品身体
	@RequestMapping(value = "/frame/product_main.do")
	public String product_main(){
		return "frame/product_main";
	}
	//商品身体--左
	@RequestMapping(value = "/frame/product_left.do")
	public String product_left(){
		return "frame/product_left";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*	
	@Autowired
	private TestTbService testTbService;*/
	//测试
	/**
	 * ModelAndView :  解  （不用）
	 * void         :  异步时
	 * String       :  同步时 return "index"  model.addAttribute()
	 */
/*	@RequestMapping(value = "/control/index1.do")
	public String index1(){
		//遍历 测试表中的用户
		TestTb testTb = new TestTb();
		testTb.setName("范冰冰5");
		testTb.setBirthday(new Date());
		testTbService.insertTestTb(testTb);
		return "index";
	}*/
	
	
}
