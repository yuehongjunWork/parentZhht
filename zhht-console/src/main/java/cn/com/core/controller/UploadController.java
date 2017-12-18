package cn.com.core.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import cn.com.common.web.Constants;
import cn.com.core.service.product.UploadService;
import net.fckeditor.response.UploadResponse;

/**
 * 上传图片  
 * 上传Fck
 * 上传商品多图片
 * @author lx
 *
 */
@Controller
public class UploadController {

	@Autowired
	private UploadService uploadService;
	
	//上传单张图片
	@RequestMapping(value = "/upload/uploadPic.do")
	public void uploadPic(@RequestParam(required = false) MultipartFile pic
			,HttpServletResponse response) throws IOException, Exception{
		//Shift + Ctrl + I
		System.out.println(pic.getOriginalFilename());
		//group1/M00/00/01/wKjIgFWOYc6APpjAAAD-qk29i78248.jpg
		String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());
		
		//全路径
		String url = Constants.img_url + path;
		
		JSONObject jo = new JSONObject();
		jo.put("url", url);
		jo.put("path", path);
		//json
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(jo.toString());
		
	}
	//上传多张图片
	@RequestMapping(value = "/upload/uploadPics.do")
	public @ResponseBody 
	List<String> uploadPics(@RequestParam(required = false) MultipartFile[] pics
			,HttpServletResponse response) throws IOException, Exception{
		//List集合 保存一堆url
		List<String> list = new ArrayList<String>();
		//遍历数组
		for (MultipartFile pic : pics) {
			//Shift + Ctrl + I
			System.out.println(pic.getOriginalFilename());
			//group1/M00/00/01/wKjIgFWOYc6APpjAAAD-qk29i78248.jpg
			String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());
			
			//全路径
			String url = Constants.img_url + path;
			
			//添加一个
			list.add(url);
			
		}
		return list;
	}
	//上传Fck的图片
	@RequestMapping(value = "/upload/uploadFck.do")
	public void uploadFck(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception{
		//无敌版接收   K : V 
		//Spring公司  MutipartRequest 对原生态的request进行强转
		MultipartRequest mr = (MultipartRequest)request;
		//只一张图片
		Map<String, MultipartFile> fileMap = mr.getFileMap();
		Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
		for (Entry<String, MultipartFile> entry : entrySet) {
			MultipartFile pic = entry.getValue();
			//group1/M00/00/01/wKjIgFWOYc6APpjAAAD-qk29i78248.jpg
			String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());
			//全路径
			String url = Constants.img_url + path;
			//返回全路径给Fck
			// fck-core.2.6.jar  java-core-2.6.jar
			UploadResponse ok = UploadResponse.getOK(url);
			//字节流
			response.getWriter().print(ok);
		}
		
		
		
		
	}
}
