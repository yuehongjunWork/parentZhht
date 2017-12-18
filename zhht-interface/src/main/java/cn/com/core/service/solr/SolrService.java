package cn.com.core.service.solr;

import cn.itcast.common.page.Pagination;

public interface SolrService {
	
	//商品检索
	public Pagination selectPaginationFromSolr(Integer pageNo,String keyword,Long brandId,String price);

	
	//保存商品信息到Solr服务器
	public void insertProduct(Long id);
}
