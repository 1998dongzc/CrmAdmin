package com.dzc.admin.service.impl;

import com.dzc.admin.common.Result;
import com.dzc.admin.dao.DeviceMapper;
import com.dzc.admin.model.Device;
import com.dzc.admin.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: 董政辰
 * @date: 2021/3/10 10:31
 * @description:
 * @email：532587041@qq.com
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    @Transactional
    public Result addDeivce(Device device, int num) {
        List<Device> devicesList = new LinkedList<>();
        for (int i = 0; i < num; i++) {
            devicesList.add(device);
        }
        int res = deviceMapper.insertDevices(devicesList);
        if (res != num)
            return Result.fail("增加设备失败");
        else
            return Result.success("增加设备成功");
    }

    @Override
    public Result getAllDevices() {
        return Result.success(deviceMapper.selectAllDevices());
    }

    @Override
    @Transactional
    public Result delDevice(List<Integer> listId) {
        int res = deviceMapper.deleteByListId(listId);
        if (res != listId.size())
            return Result.fail("删除设备失败");
        else
            return Result.success("删除设备成功");
    }

    @Override
    @Transactional
    public Result setDeviceIp(Device device) {
        List<String> allIp = deviceMapper.getAllIp();
        int index = allIp.indexOf(device.getIp());
        if (index != -1) {
            return Result.fail("该IP已存在");
        }

        int res = deviceMapper.updateByPrimaryKeySelective(device);
        if (res != 1) {
            return Result.fail("修改设备IP失败");
        } else {
            return Result.success("修改设备IP成功");
        }
    }

}
