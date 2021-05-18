package com.dzc.admin.service.impl;

import com.dzc.admin.common.Result;
import com.dzc.admin.dao.*;
import com.dzc.admin.model.*;
import com.dzc.admin.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 董政辰
 * @date: 2021/3/10 19:27
 * @description:
 * @email：532587041@qq.com
 */
@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private ApplyLogMapper applyLogMapper;

    @Autowired
    private BuildingMapper buildingMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    @Transactional
    public Result addApply(Apply apply) {
        int res = applyMapper.insertSelective(apply);
        if (res != 1)
            return Result.fail("申请失败");
        return Result.success("申请成功");
    }

    @Override
    public Result getAllApplys() {
        return Result.success(applyMapper.selectAllApplys());
    }

    @Override
    @Transactional
    public Result agree(Device device, Integer num, Integer uid, Integer applyId) {

        String mess = "设备名称:" + device.getDeviceName() + "===教室:" + getLocalNameByRoomId(device.getDeviceRoomId()) + "===数量:" + num;

        ApplyLog al = new ApplyLog();
        al.setUid(uid);
        al.setMess(mess);
        al.setStatus(1);
        for (int a = 0; a < num; a++) {
            int res = deviceMapper.insertSelective(device);
            if (res != 1)
                return Result.fail("应答失败！");
        }

        int res = applyLogMapper.insertSelective(al);
        if (res != 1)
            return Result.fail("应答失败！");

        int res1 = applyMapper.deleteByPrimaryKey(applyId);
        if (res1 != 1)
            return Result.fail("应答失败！");

        return Result.success("应答成功");
    }

    @Override
    @Transactional
    public Result disagree(Device device, Integer num, Integer uid, Integer applyId) {

        String mess = "设备名称:" + device.getDeviceName() + "————教室:" + getLocalNameByRoomId(device.getDeviceRoomId()) + "————数量:" + num;

        ApplyLog al = new ApplyLog();
        al.setUid(uid);
        al.setMess(mess);
        al.setStatus(0);

        int res = applyLogMapper.insertSelective(al);
        if (res != 1)
            return Result.fail("应答失败！(增加记录)");

        int res1 = applyMapper.deleteByPrimaryKey(applyId);
        if (res1 != 1)
            return Result.fail("应答失败！(删除应答)");

        return Result.success("应答成功");
    }

    @Override
    public Result getApplyLog(Integer uid) {
        List<ApplyLog> allLogs = applyLogMapper.getAllLogs(uid);
        return Result.success(allLogs);
    }

    @Override
    @Transactional
    public Result delAllLogs(Integer uid) {
        try {
            applyLogMapper.deleteByUid(uid);
        } catch (Exception e) {
            return Result.fail("删除记录失败");
        }
        return Result.success("删除记录成功");
    }


    public String getLocalNameByRoomId(int roomId) {
        Room room = roomMapper.selectByPrimaryKey(roomId);
        Building building = buildingMapper.selectByPrimaryKey(room.getBuidlingId());
        return building.getBuildingName() + " " + room.getRoomName();
    }
}
