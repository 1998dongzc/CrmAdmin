package com.dzc.admin.controller;

import com.dzc.admin.annotation.ValidToken;
import com.dzc.admin.common.Result;
import com.dzc.admin.common.constant.Info;
import com.dzc.admin.model.Device;
import com.dzc.admin.service.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: 董政辰
 * @date: 2021/3/12 12:01
 * @description:
 * @email：532587041@qq.com
 */
@RestController
@RequestMapping("/ops")
public class LockController {

    @Autowired
    private LockService lockService;


    @ValidToken
    @RequestMapping("/lock")
    public Result lockDevice(@RequestBody Device device, HttpServletRequest request) throws IOException {
        String token = request.getHeader("user-token");
        return lockService.opsForlock(device, Info.LOCK_TEXT, token);
    }

    @ValidToken
    @RequestMapping("/unlock")
    public Result unlockDevice(@RequestBody Device device, HttpServletRequest request) throws IOException {
        String token = request.getHeader("user-token");
        return lockService.opsForlock(device, Info.UNLOCK_TEXT,token);
    }

}
