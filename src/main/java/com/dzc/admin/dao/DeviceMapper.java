package com.dzc.admin.dao;

import com.dzc.admin.model.Device;
import com.dzc.admin.vo.DeviceVo;

import java.util.List;

public interface DeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    List<DeviceVo> selectAllDevices();

    List<String> getAllIp();

    int deleteByListId(List<Integer> id);

    int insertDevices(List<Device> devices);
}