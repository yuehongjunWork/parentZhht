package cn.com.core.service.user;

public interface SessionProvider {

	
	//保存用户名到redis
	public void setAttribute(String key,String value);
	
	//获取出用户名
	public String getAttibute(String key);
	
	//保存验证码到redis
	
	//获取验证码
	
	//退出登陆
	
	
	
	
	
}
