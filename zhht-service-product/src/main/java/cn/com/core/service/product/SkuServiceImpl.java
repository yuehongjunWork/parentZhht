package cn.com.core.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.core.bean.product.Sku;
import cn.com.core.bean.product.SkuQuery;
import cn.com.core.dao.product.ColorDao;
import cn.com.core.dao.product.SkuDao;

/**
 * 库存
 * @author lx
 *
 */
@Service("skuService")
@Transactional
public class SkuServiceImpl implements SkuService {
	@Autowired
	private SkuDao skuDao;
	@Autowired
	private ColorDao colorDao;
	
	//查询Sku结果集 通过商品ID
	public List<Sku> selectSkuListByProductId(Long productId){
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.createCriteria().andProductIdEqualTo(productId);
		List<Sku> skus = skuDao.selectByExample(skuQuery);
		for (Sku sku : skus) {
			//遍历15 向数据发送sql 只三次  9 一级缓存  默认开启  
			sku.setColor(colorDao.selectByPrimaryKey(sku.getColorId()));
		}
		return skus;
	}
	
	//保存
	public void updateSku(Sku sku){
		skuDao.updateByPrimaryKeySelective(sku);
	}

}
