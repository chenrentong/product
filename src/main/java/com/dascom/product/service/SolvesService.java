package com.dascom.product.service;

import java.util.List;

import com.dascom.product.entity.Solves;
import com.dascom.product.util.PagedResult;

public interface SolvesService {

	/**
	 * 获取 解决方案集合
	 * @param pageNumber
	 * @param pageSize
	 * @param title
	 * @return
	 */
	PagedResult<Solves> findsolveList(Integer pageNumber, Integer pageSize,
			String title);

	/**
	 *  添加解决方案
	 * @param solves
	 * @return
	 * @throws Exception 
	 */
	int addSolves(Solves solves) throws Exception;
	/**
	 * 批量删除解决方案
	 * @param id
	 * @return
	 */
	int delSolves(String id);
	/**
	 * 查询解决方案 
	 * @param id
	 * @return
	 */
	Solves findSolvesByKey(Integer id);
	/**
	 * 修改解决方案
	 * @param solves
	 * @return
	 */
	int editSolves(Solves solves);
	/**
	 * 查下看置顶 解决方案
	 * @param solves
	 * @return
	 */
	List<Solves> seleteBySolves(Solves solves);

}
