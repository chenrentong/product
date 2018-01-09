package com.dascom.product.service;

import com.dascom.product.entity.CpUser;
import com.dascom.product.util.PagedResult;

public interface UserService {

	 CpUser findCpUserByUsername(String username);


	
	/**
	 * 查询User分页数据.
	 * @param like
	 * @return
	 */
	PagedResult<CpUser> findByNamePage(String like, Integer pageNumber,
			Integer pageSize);
	/**
	 * 添加用户 
	 * @param user
	 * @return
	 */
	int addUser(CpUser user);
	/**
	 * 删除管理员
	 * @param id 格式 1,2,3,6,5
	 * @return
	 */
	Integer delUser(String id);
	CpUser findUserByKey(Integer id);
	/**
	 * 更新管理员
	 * @param user
	 * @return
	 */
	int updateUser(CpUser user);
}
