package cn.com.core.service.product;

import java.util.List;

import cn.com.core.bean.product.Sku;

public interface SkuService {

	// 查询Sku结果集 通过商品ID
	public List<Sku> selectSkuListByProductId(Long productId);

	// 保存
	public void updateSku(Sku sku);

}
