package com.dzc.admin.controller;

import com.dzc.admin.annotation.ValidAdminToken;
import com.dzc.admin.annotation.ValidToken;
import com.dzc.admin.common.Result;
import com.dzc.admin.model.Apply;
import com.dzc.admin.model.Device;
import com.dzc.admin.model.UserInfo;
import com.dzc.admin.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: 董政辰
 * @date: 2021/3/10 19:28
 * @description:
 * @email：532587041@qq.com
 */
@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Autowired
    private ApplyService applyService;

    @ValidToken
    @PostMapping("/add")
    public Result applyDevice(@RequestBody Apply apply){
        return applyService.addApply(apply);
    }

    @ValidToken
    @GetMapping("/get")
    public Result getApplys(){
        return applyService.getAllApplys();
    }

    @ValidToken
    @GetMapping("/log/{uid}")
    public Result getApplyLog(@PathVariable("uid") Integer uid){
        return applyService.getApplyLog(uid);
    }

    @ValidToken
    @PostMapping("/delLogs")
    public Result delAllApplyLogs(@RequestBody UserInfo user){
        return applyService.delAllLogs(user.getId());
    }

    @ValidAdminToken
    @PostMapping("/agree/{num}/{uid}/{aid}")
    public Result agreeApply(@RequestBody Device device, @PathVariable("num") Integer num,@PathVariable("uid") Integer uid,@PathVariable("aid") Integer applyId){
        return applyService.agree(device,num,uid,applyId);
    }

    @ValidAdminToken
    @PostMapping("/disagree/{num}/{uid}/{aid}")
    public Result rejectApply(@RequestBody Device device, @PathVariable("num") Integer num,@PathVariable("uid") Integer uid,@PathVariable("aid") Integer applyId){
        return applyService.disagree(device,num,uid,applyId);
    }

}
