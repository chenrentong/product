package com.dascom.product.dao;

import com.dascom.product.entity.WorkOrder;
import com.dascom.product.entity.WorkOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkOrderMapper {
    int countByExample(WorkOrderExample example);

    int deleteByExample(WorkOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WorkOrder record);

    int insertSelective(WorkOrder record);

    List<WorkOrder> selectByExample(WorkOrderExample example);

    WorkOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WorkOrder record, @Param("example") WorkOrderExample example);

    int updateByExample(@Param("record") WorkOrder record, @Param("example") WorkOrderExample example);

    int updateByPrimaryKeySelective(WorkOrder record);

    int updateByPrimaryKey(WorkOrder record);
    /**
     * 
     * @param o
     * @return
     */
	List<WorkOrder> findWorkOrderPageByThis(WorkOrder o);
}