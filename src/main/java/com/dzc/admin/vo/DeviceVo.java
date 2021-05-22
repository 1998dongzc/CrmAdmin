package com.dzc.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: 董政辰
 * @date: 2021/3/10 13:28
 * @description:
 * @email：532587041@qq.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeviceVo {

    private Integer id;
    private String DeviceName;
    private String BuildingName;
    private String roomName;
    private Integer status;
    private String ip;
}
