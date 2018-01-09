package com.dascom.product.service;
/**
 * 文件服务
 * @author DS-033
 *
 */
public interface UploadService {
	/**
	 * 缓存文件
	 * @param path  绝对路径
	 * @param size 大小
	 * @param suffix 后缀
	 * @return
	 */
	int add(String path, String size, String suffix);

}
