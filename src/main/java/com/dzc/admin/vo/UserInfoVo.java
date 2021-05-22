package com.dzc.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: 董政辰
 * @date: 2021/3/12 13:11
 * @description:
 * @email：532587041@qq.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfoVo {

    private Integer id;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 权限
     */
    private String role;
    /**
     * 院系部门
     */
    private String department;
}
