package cn.com.common.utils;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理Request 生成CSESSIONID
 * @author lx
 *
 */
public class RequestUtils {

	
	public static String getCSESSIONID(HttpServletRequest request,HttpServletResponse response){
		//获取cookies
		Cookie[] cookies = request.getCookies();
		if(null!=cookies){
			for(Cookie cookie:cookies){
				////2:从Cookie中获取CSESSIONID
				if(cookie.getName().equals("CSESSIONID")){
					//3:如果有 直接使用
					return cookie.getValue();
				}
			}
		}
		//判断如果 没有  创建一个CSESSIONID  保存CSESSIONID到Cookie中  保存COokie写回浏览器 
		String csessionId = UUID.randomUUID().toString().replaceAll("-", "");
		//设置cookie
		Cookie cookie = new Cookie("CSESSIONID",csessionId);
		//设置路径
		cookie.setPath("/");
		//设置cookie的存活时间   -1关闭浏览器    0立即消失    >0到时候在消失    前提：没有清理cookie
		cookie.setMaxAge(-1);
		//保存返回客户端响应
		response.addCookie(cookie);
		return csessionId;
	}
}
