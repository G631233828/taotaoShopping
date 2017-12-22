package com.taotao.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.FtpUtil;
import com.taotao.common.pojo.IDUtils;
import com.taotao.service.PictureService;
@Service
public class PictureServiceImpl  implements PictureService{
	
	//spring会自动把值注入到属性中
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASEPATH}")
	private String FTP_BASEPATH;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	
	

	@Override
	public Map uploadPicture(MultipartFile uploadFile) {
		
		Map resultMap = new HashMap<>();
		
		try {
		//生成新的文件名称
		String oldName = uploadFile.getOriginalFilename();
		//生成新的文件名
		//UUID.randomUUID();
		String newName = IDUtils.genImageName();
		//截取扩展名
		newName = newName + oldName.substring(oldName.lastIndexOf("."));
		//开始图片上传
	     /*  param host FTP服务器hostname 
		 * @param port FTP服务器端口 
		 * @param username FTP登录账号 
		 * @param password FTP登录密码 
		 * @param basePath FTP服务器基础目录
		 * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
		 * @param filename 上传到FTP服务器上的文件名 
		 * @param input 输入流 
		 * @return 成功返回true，否则返回false */
		String filePath = new DateTime().toString("/yyyy/MM/dd");
		
	
			boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASEPATH, 
					filePath, newName, uploadFile.getInputStream());
			
			if(!result){
				resultMap.put("error",1);
				resultMap.put("message", "文件上传失败");
				return resultMap;
			}
				resultMap.put("error", 0);
				resultMap.put("url", IMAGE_BASE_URL+filePath+"/"+newName);
				return resultMap;
		} catch (IOException e) {
			resultMap.put("error",1);
			resultMap.put("message", "文件上传失败");
			return resultMap;
		}
		
	}

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID());
	}
	
	
}
