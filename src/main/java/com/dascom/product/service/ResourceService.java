package com.dascom.product.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dascom.product.entity.CpResource;
import com.dascom.product.entity.CpResourceExample;
import com.dascom.product.entity.CpSoftware;
import com.dascom.product.util.PagedResult;

public interface ResourceService {
	
	/**
	 * 查询应用软件
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	PagedResult<CpResourceExample> findApp(Integer pageNumber, Integer pageSize);
	/**
	 * 查看应用软件版本
	 * @param rId
	 * @return
	 */
	List<CpSoftware> findAppEdition(Integer rId);
	/**
	 * 添加 资源
	 * @param name 
	 * @param file 
	 * @param string
	 * @param imgFilePath
	 * @param filePath
	 * @param describe
	 * @param describe2 
	 * @return int
	 */
	int addresource(String type,String title, String name, MultipartFile file, String imgFilePath, String filePath,String describe);
	/**
	 * 删除资源 
	 * @param rid
	 * @return
	 */
	int delByKey(Integer rid);
	/**
	 * 批量删除 
	 * @param rid
	 * @return
	 */
	int delByKeyList(String rid);
	/**
	 * 修改应用软件
	 * @param res
	 * @return
	 */
	int updateresource(CpResource res);
	/**
	 * 查询资源
	 * @param rid
	 * @return
	 */
	CpResource findByRid(Integer rid);
	/**
	 * 模糊分页查询资源
	 * @param string 
	 * @param pageNumber
	 * @param pageSize
	 * @param like
	 * @return
	 */
	PagedResult<CpResourceExample> findApp(String string, Integer pageNumber,
			Integer pageSize, String like);
	/**
	 * 查询应用软件版本集合
	 * @param pageNumber
	 * @param pageSize
	 * @param id 
	 * @return
	 */
	PagedResult<CpSoftware> findAppVersion(Integer pageNumber, Integer pageSize, Integer id);
	/**
	 * 添加应用软件版本
	 * @param soft
	 * @return 返回-1:版本号重复  
	 */
	int addsoft(CpSoftware soft);
	/**
	 * 删除应用软件版本  
	 * @param id 格式   1,2,3,4,5
	 * @return  删除的数量
	 */
	int delVersion(String id);
	/**
	 * 修改应用软件版本
	 * @param soft
	 * @return
	 */
	int editSoft(CpSoftware soft);
	/**
	 * 查询应用版本软件 
	 * @param id
	 * @return
	 */
	CpSoftware findVersionBykey(Integer id);
	/**
	 * 查看符合以下条件的 最新版本的应用软件
	 * @param titile 应用程序名字
	 * @param system 支持系统
	 * @return
	 */
	CpSoftware findSoftwareByVersonInfo(String title, String system);
	/**
	 * 查看符合以下条件的 最新版本的应用软件 可以查看到本地文件目录
	 * @param name 软件名字. or 安全码 
	 * @return
	 */
	CpSoftware findSoftwareByVersion(String name);
	/**
	 * 查看符合以下条件的 最新版本的应用软件 可以查看到本地文件目录
	 * @param name
	 * @return
	 */
	CpSoftware findSoftwareByKey(String name);
	/**
	 * 查询资源
	 * @param typeName  类型名称
	 * @param pageNumber 
	 * @param pageSize
	 * @return
	 */
	PagedResult<CpResourceExample> findResource(String typeName,
			Integer pageNumber, Integer pageSize);
	/**
	 * 下载量+1
	 * @param soft
	 */
	void editSoftDownloadNum(CpSoftware soft);

}
