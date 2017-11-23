package com.dascom.product.dao;

import com.dascom.product.entity.CpUpload;
import com.dascom.product.entity.CpUploadExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CpUploadMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_upload
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    int countByExample(CpUploadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_upload
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    int deleteByExample(CpUploadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_upload
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_upload
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    int insert(CpUpload record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_upload
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    int insertSelective(CpUpload record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_upload
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    List<CpUpload> selectByExample(CpUploadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_upload
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    CpUpload selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_upload
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    int updateByExampleSelective(@Param("record") CpUpload record, @Param("example") CpUploadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_upload
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    int updateByExample(@Param("record") CpUpload record, @Param("example") CpUploadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_upload
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    int updateByPrimaryKeySelective(CpUpload record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_upload
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    int updateByPrimaryKey(CpUpload record);

}