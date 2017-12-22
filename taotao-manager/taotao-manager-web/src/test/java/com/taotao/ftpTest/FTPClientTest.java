package com.taotao.ftpTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FTPClientTest {

	public static FTPClient connectFTP(String url, int port, String username, String password)
			throws SocketException, IOException {
		// 创建FTP
		FTPClient ftpClient = new FTPClient();
		// ftp连接
		ftpClient.connect(url, port);
		// ftp登录
		ftpClient.login(username, password);

		return ftpClient;
	}

	public static void uploadFile(String upfileName, FileInputStream fileInputStream)
			throws SocketException, IOException {
		FTPClient client = connectFTP("192.168.1.165", 21, "ftpuser", "123456");
		System.out.println("上传文件目录为：/home/ftpuser/test/");
		client.makeDirectory("/home/ftpuser/test2/");
		client.changeWorkingDirectory("/home/ftpuser/test2/");
		client.appendFile(upfileName, fileInputStream);

		//设置文件上传格式
		client.setFileType(FTP.BINARY_FILE_TYPE);
		
		System.out.println("注销");
		client.logout();
	}

	public static void main(String[] args) {
		File f = new File("I:\\PS素材\\1.jpg");
		try {
			FileInputStream inputStream = new FileInputStream(f);
			String upfileName = "test.jpg";
			uploadFile(upfileName, inputStream);;
			System.out.println("图片上传成功");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
