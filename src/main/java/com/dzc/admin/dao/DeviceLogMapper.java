package com.dzc.admin.dao;

import com.dzc.admin.model.DeviceLog;

public interface DeviceLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeviceLog record);

    int insertSelective(DeviceLog record);

    DeviceLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeviceLog record);

    int updateByPrimaryKey(DeviceLog record);
}