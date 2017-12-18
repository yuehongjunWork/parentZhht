package cn.com.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.core.bean.product.Brand;
import cn.com.core.service.product.BrandService;
import cn.itcast.common.page.Pagination;
/**
 * 品牌管理
 * 列表
 * 修改
 * 批量删除
 * 同学完成 
 * 添加
 * 单删除
 * @author lx
 *
 */
@Controller
public class BrandController {

	@Autowired
	private BrandService brandService;
	
	//查询
	@RequestMapping(value = "/brand/list.do")
	public String list(Integer pageNo,String name,Integer isDisplay,Model model){
		
		//返回分页对象光标在本行最后  Shift + ALt + L
		Pagination pagination = brandService.selectPaginationByQuery(pageNo, name, isDisplay);
		model.addAttribute("pagination", pagination);
		model.addAttribute("name", name);
		model.addAttribute("isDisplay", isDisplay);
		return "brand/list";
	}
	
	//去修改页面
	@RequestMapping(value = "/brand/toEdit.do")
	public String toEdit(Long id,Model model){
		
		Brand brand = brandService.selectBrandById(id);
		model.addAttribute("brand", brand);
		
		return "brand/edit";
		
	}
	
	//去修改页面
	@RequestMapping(value = "/brand/toAdd.do")
	public String toAdd(){
		
		return "brand/add";
			
	}
	
	//修改页面
	@RequestMapping(value = "/brand/edit.do")
	public String edit(Brand brand){
		
		brandService.updateBrand(brand);
		return "redirect:/brand/list.do";
	}
	//批量删除
	@RequestMapping(value = "/brand/deletes.do")
	public String deletes(Long[] ids,Integer pageNo,String name,Integer isDisplay){
		brandService.deletes(ids);
		
		return "forward:/brand/list.do";
	}
	
}
