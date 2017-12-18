package cn.com.core.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.common.utils.RequestUtils;
import cn.com.core.bean.user.Buyer;
import cn.com.core.service.user.BuyerService;
import cn.com.core.service.user.SessionProvider;

/**
 * 登陆
 * 去登陆页面
 * 提交登陆表单
 * 密码加密  MD5 + 十六进加密       加盐
 * @author lx
 *
 */
@Controller
public class LoginController {

	
	//去登陆页面
	@RequestMapping(value = "/shopping/login.aspx",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	
	@Autowired
	private BuyerService buyerService;
	@Autowired
	private SessionProvider sessionProvider;
	
	//提交表单的登陆
	@RequestMapping(value = "/shopping/login.aspx",method=RequestMethod.POST)
	public String login(String username,String password,String returnUrl,Model model
			,HttpServletRequest request,HttpServletResponse response){
		
		//判断用户名不能为空  "" "  "  Converter转换器
		if(null != username){
			//判断密码不能为空
			if(null != password){
				//用户名必须正确
				Buyer buyer = buyerService.selectBuyerByUsername(username);
				if(null != buyer){
					//密码必须正确
					if(encodePassowrd(password).equals(buyer.getPassword())){
						//保存用户名到Session中  csessionid
						sessionProvider.setAttribute(
								RequestUtils.getCSESSIONID(request, response), buyer.getUsername());
						//回跳回之前访问页面
						return "redirect:" + returnUrl;
					}else{
						model.addAttribute("error", "密码必须正确");
					}
				}else{
					model.addAttribute("error", "用户名必须正确");
				}
			}else{
				
				model.addAttribute("error", "密码不能为空");
			}
			
		}else{
			model.addAttribute("error", "用户名不能为空");
		}
		
		return "login";
	}
	
	//加密     
	public String encodePassowrd(String password){
		
		//password = "sdafsafsafs123456asdfsafafsafsafdsafsasa";
		
		//jdk md5
		String algorithm = "MD5";
		char[] encodeHex = null;
		try {
			MessageDigest instance = MessageDigest.getInstance(algorithm);
			//MD5之后密文
			byte[] digest = instance.digest(password.getBytes());
			//十六进制加密 
			encodeHex = Hex.encodeHex(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(encodeHex);
	}
	
	public static void main(String[] args) {
		LoginController l = new LoginController();
		String e = l.encodePassowrd("123456");
		System.out.println(e);
	}
	//判断用户是否登陆
	@RequestMapping(value = "/shopping/isLogin.aspx")
	public @ResponseBody
	MappingJacksonValue isLogin(String callback, HttpServletRequest request,HttpServletResponse response){
		String result = "0";//<script
		//用户名
		String username = sessionProvider.getAttibute(RequestUtils.getCSESSIONID(request, response));
		if(null != username){
			result = "1";
		}
		//jsonp返回值
		MappingJacksonValue mjv = new MappingJacksonValue(result);
		mjv.setJsonpFunction(callback);
		return mjv;
	}
	
}
