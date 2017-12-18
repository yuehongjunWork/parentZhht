package cn.com.core.dao.product;

import java.util.List;

import cn.com.core.bean.product.Brand;
import cn.com.core.bean.product.BrandQuery;

/**
 * 品牌
 * @author lx
 *
 */
public interface BrandDao {
	//第一步：查询结果集  （条件） 名称  是否可用   limit 开始行 ，每页数
	public List<Brand> selectBrandListByQuery(BrandQuery brandQuery);

	//第二步：查询总条件数 （条件） 名称  是否可用
	public Integer selectBrandCountByQuery(BrandQuery brandQuery);
	//通过ID查询一个品牌
	public Brand selectBrandById(Long id);
	
	//修改
	public void updateBrand(Brand brand);
	
	//批量删除
	public void deletes(Long[] ids);

}
