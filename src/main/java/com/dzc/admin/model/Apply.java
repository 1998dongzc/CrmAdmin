package com.dzc.admin.model;

import java.util.Date;

public class Apply {
    private Integer id;

    private Integer roomId;

    private String deviceName;

    private String localName;

    private Integer num;

    private Integer uId;

    private Date createTime;

    private Integer code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName == null ? null : localName.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", deviceName='" + deviceName + '\'' +
                ", localName='" + localName + '\'' +
                ", num=" + num +
                ", uId=" + uId +
                ", createTime=" + createTime +
                ", code=" + code +
                '}';
    }
}