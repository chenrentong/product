package com.dascom.product.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dascom.product.dao.WorkOrderMapper;
import com.dascom.product.entity.WorkOrder;
import com.dascom.product.service.WorkOrderService;
import com.dascom.product.util.PageBeanUtil;
import com.dascom.product.util.PagedResult;
import com.github.pagehelper.PageHelper;

@Service
public class WorkOrderServiceImpl implements WorkOrderService {

	@Autowired
	private WorkOrderMapper workOrderMapper;
	
	@Override
	public int addworkOrder(WorkOrder workOrder) {
		workOrder.setStartTime(new Date());
		return workOrderMapper.insertSelective(workOrder);
	}

	@Override
	public PagedResult<WorkOrder> findWorkOrderPage(Integer pageNumber,
			Integer pageSize, Integer isSolve, String like) throws Exception {
		if(pageNumber!=null&&pageSize!=null)
			PageHelper.startPage(pageNumber,pageSize);
		
		WorkOrder o=new WorkOrder();
		if(isSolve==null||isSolve==1||isSolve==0){
			o.setIsSolve(isSolve);
		}
		o.setTheme(like!=null?"%"+like+"%":null);
		
		return PageBeanUtil.toPagedResult(workOrderMapper.findWorkOrderPageByThis(o));
	}

	@Override
	public WorkOrder findByKey(Integer id) {
		return workOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	public int update(Integer id, Integer isSolve, String solveProject) {
		WorkOrder order=new WorkOrder();
		order.setId(id);
		order.setIsSolve(isSolve);
		order.setSolveProject(solveProject);
		return workOrderMapper.updateByPrimaryKeySelective(order);
	}

}
