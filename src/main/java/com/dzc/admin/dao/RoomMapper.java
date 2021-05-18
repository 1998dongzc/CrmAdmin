package com.dzc.admin.dao;

import com.dzc.admin.model.Room;

import java.util.List;

public interface RoomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    List<Room> selectRooms();

    List<Room> selectRoomsByBuildingId(Integer bid);
}