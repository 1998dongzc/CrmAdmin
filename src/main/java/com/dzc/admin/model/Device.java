package com.dzc.admin.model;

public class Device {
    private Integer id;

    private String deviceName;

    private Integer deviceRoomId;

    private Integer status;

    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public Integer getDeviceRoomId() {
        return deviceRoomId;
    }

    public void setDeviceRoomId(Integer deviceRoomId) {
        this.deviceRoomId = deviceRoomId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", deviceName='" + deviceName + '\'' +
                ", deviceRoomId=" + deviceRoomId +
                ", status=" + status +
                ", Ip=" + ip +
                '}';
    }
}