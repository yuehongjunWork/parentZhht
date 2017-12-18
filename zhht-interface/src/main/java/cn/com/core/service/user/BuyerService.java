package cn.com.core.service.user;

import cn.com.core.bean.BuyerCart;
import cn.com.core.bean.order.Order;
import cn.com.core.bean.product.Sku;
import cn.com.core.bean.user.Buyer;

public interface BuyerService {

	// 用户名查询用户
	public Buyer selectBuyerByUsername(String username);

	// 保存购物车中所有商品到Redis中
	public void insertBuyerCartToRedis(BuyerCart buyerCart, String username);
	
	//登陆获取Redis中购物车
	public BuyerCart selectBuyerCartFromRedis(String username);
	
	
	//给购物车装数据
	//入参： skuId\
	//返回值  Sku    商品对象  颜色对象
	public Sku selectSkuById(Long skuId);
	
	
	//保存订单
	public void insertOrder(Order order,String username);

}
