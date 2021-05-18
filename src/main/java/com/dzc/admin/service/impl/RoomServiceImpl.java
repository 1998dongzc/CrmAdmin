package com.dzc.admin.service.impl;

import com.dzc.admin.common.Result;
import com.dzc.admin.dao.BuildingMapper;
import com.dzc.admin.dao.RoomMapper;
import com.dzc.admin.model.Building;
import com.dzc.admin.model.Room;
import com.dzc.admin.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: 董政辰
 * @date: 2021/3/11 20:44
 * @description:
 * @email：532587041@qq.com
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private BuildingMapper buildingMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    @Transactional
    public Result addBuild(Building building) {
        int res = buildingMapper.insertSelective(building);
        if (res!=1)
            return Result.fail("增加楼栋失败");
        else
            return Result.success("增加楼栋成功");
    }

    @Override
    @Transactional
    public Result delBuild(Building building) {
        int res = buildingMapper.deleteByPrimaryKey(building.getId());
        if (res!=1)
            return Result.fail("删除楼栋失败");
        else
            return Result.success("删除楼栋成功");
    }

    @Override
    @Transactional
    public Result addRoom(Room room) {
        int res = roomMapper.insertSelective(room);
        if (res!=1)
            return Result.fail("增加教室失败");
        else
            return Result.success("增加教室成功");
    }

    @Override
    @Transactional
    public Result delRoom(Room room) {
        int res = roomMapper.deleteByPrimaryKey(room.getId());
        if (res!=1)
            return Result.fail("删除教室失败");
        else
            return Result.success("删除教室成功");
    }

}
