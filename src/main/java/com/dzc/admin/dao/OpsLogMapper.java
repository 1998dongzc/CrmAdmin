package com.dzc.admin.dao;

import com.dzc.admin.model.OpsLog;
import com.dzc.admin.vo.OpsLogVo;

import java.util.List;

public interface OpsLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OpsLog record);

    int insertSelective(OpsLog record);

    OpsLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OpsLog record);

    int updateByPrimaryKey(OpsLog record);

    List<OpsLogVo> getAllLogs();
}