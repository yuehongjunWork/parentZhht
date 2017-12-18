package cn.com.core.service.product;

import org.springframework.stereotype.Service;

import cn.com.common.fdfs.FastDFSUtils;
/**
 * 上传图片 
 * @author lx
 *
 */
@Service("uploadService")
public class UploadServiceImpl implements UploadService{

	
	//上传
	public String uploadPic(byte[] pic ,String name,Long size) throws Exception{
		return FastDFSUtils.uploadPic(pic, name, size);
	}
}
