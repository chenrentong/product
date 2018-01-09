package com.dascom.product.dao;

import com.dascom.product.entity.Solves;
import com.dascom.product.entity.SolvesExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SolvesMapper {
    int countByExample(SolvesExample example);

    int deleteByExample(SolvesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Solves record);

    int insertSelective(Solves record);

    List<Solves> selectByExample(Solves solves);

    Solves selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Solves record, @Param("example") SolvesExample example);

    int updateByExample(@Param("record") Solves record, @Param("example") SolvesExample example);

    int updateByPrimaryKeySelective(Solves record);

    int updateByPrimaryKey(Solves record);
    /**
     *  解决方案集合
     * @param title 标题
     * @return
     */
	List<Solves> selectBytitle(@Param("title")String title);
}