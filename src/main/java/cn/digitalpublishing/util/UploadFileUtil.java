package cn.digitalpublishing.util;

import java.io.File;
import java.io.FileOutputStream;

public class UploadFileUtil {
	/**
	 * 将上传文件写入到FTP服务器
	 * 
	 * @param webRoot
	 * @param filePath
	 * @param b
	 */
	public static void writeFile(String webRoot, String filePath, byte[] b) {
		//文件上传
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(new File(PathUtil.bulidFullPath(webRoot, filePath)));
			outputStream.write(b);
			outputStream.close();
		} catch (Exception e) {
			File file = new File(filePath);
			if(file.exists()){
				file.delete();
			}
		}
	}
	
	//解析文件完毕后，将服务器上的文件删除
	public static void deleteFile(String webRoot, String filePath) {
			File file = new File(PathUtil.bulidFullPath(webRoot, filePath));
			if(file.exists()){
				file.delete();
		}
	}
	
	/**
	 * 使用UUID创建上传文件的名字
	 * @param imageFormat
	 * @return
	 */
	public static String createUploadFileName(String imageFormat) {
		String uuid = java.util.UUID.randomUUID().toString();
		StringBuffer uploadFileName = new StringBuffer();
		uploadFileName.append(uuid.replaceAll(PathUtil.JUNCTION, ""));
		uploadFileName.append(PathUtil.DOT);
		uploadFileName.append(imageFormat);
		return uploadFileName.toString();
	}
	
	public static void main(String[] args) {
		//文件上传
		FileOutputStream outputStream = null;
		byte[] b = null;
		try {
			outputStream = new FileOutputStream(new File("D:/ljbd.txt"));
			outputStream.write(b);
			outputStream.close();
		} catch (Exception e) {
			File file = new File("D:/ljbd.txt");
			if(file.exists()){
				file.delete();
			}
		}
	}
}
