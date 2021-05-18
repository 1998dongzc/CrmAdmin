package com.dzc.admin.service;

import com.dzc.admin.common.Result;
import com.dzc.admin.model.Apply;
import com.dzc.admin.model.Device;

import java.util.List;

/**
 * @author: 董政辰
 * @date: 2021/3/10 10:30
 * @description:
 * @email：532587041@qq.com
 */
public interface DeviceService {

    Result addDeivce(Device device, int num);

    Result getAllDevices();

    Result delDevice(List<Integer> id);

    Result setDeviceIp(Device device);
}
