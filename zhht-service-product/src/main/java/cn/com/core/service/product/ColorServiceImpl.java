package cn.com.core.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.core.bean.product.Color;
import cn.com.core.bean.product.ColorQuery;
import cn.com.core.dao.product.ColorDao;

/**
 * 颜色
 * @author lx
 *
 */
@Service("colorService")
public class ColorServiceImpl implements ColorService{

	@Autowired
	private ColorDao colorDao;
	@Override
	public List<Color> selectColorList() {
		// TODO Auto-generated method stub
		ColorQuery colorQuery = new ColorQuery();
		colorQuery.createCriteria().andParentIdNotEqualTo(0L);
		return colorDao.selectByExample(colorQuery);
	}

}
