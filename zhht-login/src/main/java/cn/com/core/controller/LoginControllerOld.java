package cn.com.core.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.core.bean.TestTb;
import cn.com.core.service.TestTbService;

import com.alibaba.dubbo.config.annotation.Reference;




@Controller
public class LoginControllerOld {
//    @Reference
    @Autowired
	private TestTbService testTbService;
    
    @Autowired
    @Qualifier("testTbService")
	private TestTbService testTbService1;
    
    @Autowired
    @Qualifier("testTbService")
	private TestTbService testTbService2;

	
	@RequestMapping(value="/shopping/index.aspx",method=RequestMethod.POST)
	@ResponseBody
	public String index(String username,String password,String returnUrl,Model model,
			HttpServletRequest request,HttpServletResponse response){
		return "/index.jsp"; 
	}
	
	@RequestMapping(value="/shopping/login.aspx",method=RequestMethod.GET)
	@ResponseBody
	public String register(String username,String password,String returnUrl,Model model,
			HttpServletRequest request,HttpServletResponse response){
		System.out.println("======register");
		TestTb testTb = new TestTb();
		String message = "";
		if(username==null||password==null){
			message = "用户名或密码不能为空";
			model.addAttribute("message", message);            
            return "error";
		}else{
			try {
				testTb.setName(username);
				testTb.setBirthday(new Date());
				testTbService.addTestTb(testTb);
				System.out.println("注册成功");
				message = "注册成功";
				model.addAttribute("message", message);
				return "success";
			} catch (Exception e) {
				System.out.println("异常");
				e.printStackTrace();
				message = "用户名或密码不能为空";
				model.addAttribute("message", message);
				return "error";
			}
		}
}
	
	/*@RequestMapping(value="/shopping/login.aspx",method=RequestMethod.POST)
	public String login(String username,String password,String returnUrl,Model model,
			HttpServletRequest request,HttpServletResponse response){
		//用户名不为空
		if(null!=username){
			//密码不为空
			if(null!=password){
				//用户名是否存在
				TestTb buyer = testTbService.insertTestTb(testTb);
				if(null!=buyer){
					if(encodePassowrd(password).equals(buyer.getPassword())){
						sessionProvider.setAttribute(RequestUtils.getCSESSIONID(request, response),username);
						//回跳回之前访问页面
						return "redirect:" + returnUrl;
					}else{
						model.addAttribute("error", "密码不正确");
					}
				}else{
					model.addAttribute("error", "用户名不存在");
				}
			}else{
				model.addAttribute("error", "密码不为空");
			}
		}else{
			model.addAttribute("error", "用户名不为空");
		}
		
		return "login";
	}
	
	
	
	//判断用户是否已经登录
	@RequestMapping(value="shopping/isLogin.aspx")
	public @ResponseBody 
	MappingJacksonValue isLogin(String callback,HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		
		String result = "0";
		String username = sessionProvider.getAttribute(RequestUtils.getCSESSIONID(request, response));
		if(null!=username){
			result = "1";
		}
		//jsonp返回值
		MappingJacksonValue mjv = new MappingJacksonValue(result);
		mjv.setJsonpFunction(callback);
		return mjv;
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print(result);
		
	}
	*/
	//加密     MD5(jdk)  16进制加密(apache)    加盐
	public String encodePassowrd(String password){
		//jdk md5
		String algorithm = "MD5";
		char[] encodeHex = null;
		try {
			MessageDigest instance = MessageDigest.getInstance(algorithm);
			//MD5加密
			 byte[] digest = instance.digest(password.getBytes());
			
			//十六进制加密  apache
			 encodeHex = Hex.encodeHex(digest);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return String.valueOf(encodeHex);
		
	}
	
	
	
}
