package com.dzc.admin.dao;

import com.dzc.admin.model.ApplyLog;

import java.util.List;

public interface ApplyLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApplyLog record);

    int insertSelective(ApplyLog record);

    ApplyLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApplyLog record);

    int updateByPrimaryKey(ApplyLog record);

    List<ApplyLog> getAllLogs(Integer uid);

    int deleteByUid(Integer uid);
}