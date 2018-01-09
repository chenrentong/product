package com.dascom.product.service;

import com.dascom.product.entity.WorkOrder;
import com.dascom.product.util.PagedResult;

/**
 * 工单中心服务接口
 * @author DS-033
 *
 */
public interface WorkOrderService {
	/**
	 * 提交工单
	 * @param workOrder
	 * @return
	 */
	int addworkOrder(WorkOrder workOrder);
	/**
	 * 分页查询工单
	 * @param pageNumber
	 * @param pageSize
	 * @param isSolve
	 * @param like
	 * @return
	 */
	PagedResult<WorkOrder> findWorkOrderPage(Integer pageNumber,
			Integer pageSize, Integer isSolve, String like) throws Exception;
	/**
	 * 查询单个工单
	 * @param id
	 * @return
	 */
	WorkOrder findByKey(Integer id);
	/**
	 * 修改工单
	 * @param id
	 * @param isSolve
	 * @param solveProject
	 * @return
	 */
	int update(Integer id, Integer isSolve, String solveProject);
}
