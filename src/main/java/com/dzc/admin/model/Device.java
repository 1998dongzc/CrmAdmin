package com.dzc.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Device {
    private Integer id;

    private String deviceName;

    private Integer deviceRoomId;

    private Integer status;

    private String ip;
}