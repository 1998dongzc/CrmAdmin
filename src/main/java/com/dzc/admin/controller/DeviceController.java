package com.dzc.admin.controller;

import com.dzc.admin.annotation.ValidAdminToken;
import com.dzc.admin.annotation.ValidToken;
import com.dzc.admin.common.Result;
import com.dzc.admin.model.Device;
import com.dzc.admin.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: 董政辰
 * @date: 2021/3/10 10:28
 * @description:
 * @email：532587041@qq.com
 */
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @ValidAdminToken
    @PostMapping("/add/{num}")
    public Result addDevice(@RequestBody Device device, @PathVariable("num") int num){
        System.out.println(device);
        return deviceService.addDeivce(device,num);
    }

    @ValidToken
    @GetMapping("/get")
    public Result getDevice(){
        return deviceService.getAllDevices();
    }

    @ValidAdminToken
    @PostMapping("/del/{idList}")
    public Result delDevice(@PathVariable("idList") List<Integer> id){
        return deviceService.delDevice(id);
    }

    @ValidAdminToken
    @PostMapping("/setDeviceIp")
    public Result updateDeviceIp(@RequestBody Device device){
        return deviceService.setDeviceIp(device);
    }
}
