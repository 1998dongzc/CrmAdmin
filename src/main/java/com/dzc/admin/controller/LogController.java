package com.dzc.admin.controller;

import com.dzc.admin.annotation.ValidToken;
import com.dzc.admin.common.Result;
import com.dzc.admin.dao.OpsLogMapper;
import com.dzc.admin.vo.OpsLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 董政辰
 * @date: 2021/3/18 11:41
 * @description:
 * @email：532587041@qq.com
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private OpsLogMapper opsLogMapper;

    @ValidToken
    @GetMapping("/getOpsLog")
    public Result getAllOpsLog(){
        OpsLogVo lo=new OpsLogVo();
        return Result.success(opsLogMapper.getAllLogs());
    }

}
