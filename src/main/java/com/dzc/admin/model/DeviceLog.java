package com.dzc.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeviceLog {
    private Integer id;

    private String operation;

    private Integer deviceId;

    private Integer userId;

 }