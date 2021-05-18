package com.dzc.admin.service;

import com.dzc.admin.common.Result;
import com.dzc.admin.model.Apply;
import com.dzc.admin.model.Device;

/**
 * @author: 董政辰
 * @date: 2021/3/10 19:26
 * @description:
 * @email：532587041@qq.com
 */
public interface ApplyService {

    Result addApply(Apply apply);

    Result getAllApplys();

    Result agree(Device device,Integer num,Integer uid,Integer applyId);

    Result getApplyLog(Integer uid);

    Result delAllLogs(Integer uid);

    Result disagree(Device device, Integer num, Integer uid, Integer applyId);
}
