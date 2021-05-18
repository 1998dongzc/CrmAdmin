package com.dzc.admin.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 董政辰
 * @date: 2021/3/3 17:25
 * @description: 后端传给前端的消息体
 * @email：532587041@qq.com
 */

//Lombok自动生成get set和有参无参构造函数
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    /**
     * 请求结果值
     * 成功:200
     * 错误依据情况而定
     */
    private int status;

    /**
     * 请求返回的通知消息内容
     */
    private String message;

    /**
     * 请求返回的结果内容
     */
    private Object data;


    /**
     * 返回正确提示 无消息 无数据
     */
    public static Result success() {
        return new Result(200, "", null);
    }

    /**
     * 返回正确提示 有提示 无数据
     */
    public static Result success(String message) {
        return new Result(200, message, null);
    }

    /**
     * 返回正确提示 无提示 有数据
     */
    public static Result success(Object data) {
        return new Result(200, "", data);
    }

    /**
     * 返回正确提示 有提示 有数据
     */
    public static Result success(String message, Object data) {
        return new Result(200, message, data);
    }

    /**
     * 返回500错误 有消息
     */
    public static Result fail(String message) {
        return new Result(500, message, null);
    }

    /**
     * 返回自定义错误  有消息
     */
    public static Result fail(Integer errCode, String message) {
        return new Result(errCode, message, null);
    }


    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
