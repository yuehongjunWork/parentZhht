package cn.com.core.service.cms;

import java.util.List;

import cn.com.core.bean.product.Product;
import cn.com.core.bean.product.Sku;

public interface CmsService {
	
	
	//查询商品表
	public Product selectProductById(Long id);
	
	/*	库存表  里面颜色表
	颜色表*/
	public List<Sku> selectSkuListByProductId(Long productId);

}
