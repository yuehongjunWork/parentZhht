package cn.com.core.service.product;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.common.page.Pagination;
import cn.com.core.bean.product.Product;
import cn.com.core.bean.product.ProductQuery;
import cn.com.core.bean.product.ProductQuery.Criteria;
import cn.com.core.bean.product.Sku;
import cn.com.core.bean.product.SkuQuery;
import cn.com.core.dao.product.ProductDao;
import cn.com.core.dao.product.SkuDao;
import redis.clients.jedis.Jedis;

/**
 * 商品
 * 
 * @author lx
 *
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	
	@Autowired
	private ProductDao productDao;
	// 返回分页对象 通过条件查询
	public Pagination selectPaginationByQuery(Integer pageNo, String name, Long brandId, Boolean isShow) {
		ProductQuery productQuery = new ProductQuery();
		//当前页
		productQuery.setPageNo(Pagination.cpn(pageNo));
		//每页数
		productQuery.setPageSize(10);
		//id 倒排 
		productQuery.setOrderByClause("id desc");
		//条件对象
		Criteria createCriteria = productQuery.createCriteria();
		
		StringBuilder params = new StringBuilder();
		//判断条件
		if(null != name){
			createCriteria.andNameLike("%" + name + "%");
			
			params.append("name=").append(name);
		}
		//上下架
		if(null != isShow){
			createCriteria.andIsShowEqualTo(isShow);
			params.append("&isShow=").append(isShow);
		}else{
			createCriteria.andIsShowEqualTo(false);
			params.append("&isShow=").append(false);
		}
		//品牌ID
		if(null != brandId){
			createCriteria.andBrandIdEqualTo(brandId);
			params.append("&brandId=").append(brandId);
		}
		//分页
		Pagination pagination = new Pagination(
				productQuery.getPageNo(),
				productQuery.getPageSize(),
				productDao.countByExample(productQuery)
		);
		//结果集
		pagination.setList(productDao.selectByExample(productQuery));
		//分页的页面展示
		String url = "/product/list.do";
		pagination.pageView(url, params.toString());
		return pagination;
	}
	
	
	@Autowired
	private SkuDao skuDao;
	@Autowired
	private Jedis jedis;
	//保存商品
	public void insertProduct(Product product){
//		保存商品
		Long id = jedis.incr("pno");
		product.setId(id);
		//上下架   下架  false
		product.setIsShow(false);
		//是否删除    不删除  true
		product.setIsDel(true);
		//添加时间   数据库要求必填项
		product.setCreateTime(new Date());
		//保存商品  insert into bbs_product (非Null字段) values (‘好东西’,"","")
		productDao.insertSelective(product);
//		保存库存
		//遍历颜色
		for(String color : product.getColors().split(",")){
			//遍历尺码 
			for(String size : product.getSizes().split(",")){
				//设置商品ID
				Sku sku = new Sku();
				sku.setProductId(product.getId());
				//颜色ID
				sku.setColorId(Long.parseLong(color));
				//尺码 
				sku.setSize(size);
				//市场价
				sku.setMarketPrice(888f);
				//售
				sku.setPrice(666f);
				//库存 
				sku.setStock(222);
				//运费
				sku.setDeliveFee(10f);
				//购买限制 200
				sku.setUpperLimit(200);
				//时间
				sku.setCreateTime(new Date());
				
				//保存库存 
				skuDao.insertSelective(sku);
				
				
				
			}
			
		}
	}

	@Autowired
	private JmsTemplate jmsTemplate;
	//上架
	public void isShow(Long[] ids){
		//商品对象
		Product product = new Product();
		product.setIsShow(true);
		for (final Long id : ids) {
			//1：更商品状态 
			product.setId(id);// update bbs_product set is_show = 1 where id = 500
			productDao.updateByPrimaryKeySelective(product);
			//发送消息 到MQ
			jmsTemplate.send(new MessageCreator(){
				@Override
				public Message createMessage(Session session) throws JMSException {
					// TODO Auto-generated method stub
					return session.createTextMessage(String.valueOf(id));
				}
				
			});
		}
		
		
		
		
	}
}
