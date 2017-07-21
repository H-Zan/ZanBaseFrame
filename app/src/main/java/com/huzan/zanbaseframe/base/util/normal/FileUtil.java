package com.huzan.zanbaseframe.base.util.normal;

import java.io.File;

/**
 * Created by ZAN on 2017/7/19.
 * 
 */

public class FileUtil {
	
	/**
	 * 判断文件夹是否存在,如果不存在则创建文件夹
	 */
	public static File isFileExist(String path) {
		File file = new File(path);
		if (!file.exists()) {
			try {
				boolean mkdirs = file.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return file;
	}
}
