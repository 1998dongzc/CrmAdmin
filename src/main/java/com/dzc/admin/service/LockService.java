package com.dzc.admin.service;

import com.dzc.admin.common.Result;
import com.dzc.admin.model.Device;

import java.io.IOException;

/**
 * @author: 董政辰
 * @date: 2021/3/16 18:03
 * @description:
 * @email：532587041@qq.com
 */
public interface LockService {

    Result opsForlock(Device device,String ops,String token) throws IOException;

}
