package com.dzc.admin.model;

public class Room {
    private Integer id;

    private String roomName;

    private Integer buidlingId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    public Integer getBuidlingId() {
        return buidlingId;
    }

    public void setBuidlingId(Integer buidlingId) {
        this.buidlingId = buidlingId;
    }
}