package cn.com.core.service.product;

import java.util.List;

import cn.itcast.common.page.Pagination;
import cn.com.core.bean.product.Brand;

public interface BrandService {
	
	
	//返回分页对象
	public Pagination selectPaginationByQuery(Integer pageNo,String name,Integer isDisplay);
	
	//查询所有可见的品牌
	public List<Brand> selectBrandListByQuery(Integer isDisplay);
	
	//通过ID查询一个品牌
	public Brand selectBrandById(Long id);
	
	//修改
	public void updateBrand(Brand brand);
	
	
	//批量删除
	public void deletes(Long[] ids);
	
	//查询Redis中的品牌结果集
	public List<Brand> selectBrandListFromRedis();

}
