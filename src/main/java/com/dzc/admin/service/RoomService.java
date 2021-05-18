package com.dzc.admin.service;

import com.dzc.admin.common.Result;
import com.dzc.admin.model.Building;
import com.dzc.admin.model.Room;

/**
 * @author: 董政辰
 * @date: 2021/3/11 20:43
 * @description:
 * @email：532587041@qq.com
 */
public interface RoomService {
    Result addBuild(Building building);

    Result delBuild(Building building);

    Result addRoom(Room room);

    Result delRoom(Room room);
}
