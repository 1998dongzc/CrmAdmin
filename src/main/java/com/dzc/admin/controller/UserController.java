package com.dzc.admin.controller;

import com.dzc.admin.annotation.ValidAdminToken;
import com.dzc.admin.annotation.ValidToken;
import com.dzc.admin.common.Result;
import com.dzc.admin.model.User;
import com.dzc.admin.model.UserInfo;
import com.dzc.admin.service.UserService;
import com.dzc.admin.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: 董政辰
 * @date: 2021/3/3 17:18
 * @description: 用户账号相关controller
 * @email：532587041@qq.com
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录APi
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return userService.logIn(user);
    }

    /**
     * 用户注册
     */
    @PostMapping("/signUp")
    public Result signUp(@RequestBody UserInfoVo userInfoVo){
        System.out.println(userInfoVo);
        return userService.signUpOneUser(userInfoVo);
    }

    /**
     * 获取用户信息api
     *
     * @return
     */
    @GetMapping("/info")
    @ValidToken
    public Result getUserInfo(HttpServletRequest request) {
        return userService.getUserInfo(request);
    }

    @ValidToken
    @PostMapping("/get")
    public Result getUser(@RequestBody User user){
        return userService.getUser(user);
    }

    @ValidToken
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @ValidToken
    @GetMapping("/getInfo/{id}")
    public Result getUserName(@PathVariable("id") Integer id){
        return userService.getUserInfoById(id);
    }

    @ValidToken
    @PostMapping("/updateInfo")
    public Result updateUserInfo(@RequestBody UserInfo userInfo){
        return userService.updateUserInfo(userInfo);
    }

    @ValidAdminToken
    @GetMapping("/getAllUser")
    public Result getAllUser(){
        return userService.getAllUserInfo();
    }

    @ValidAdminToken
    @PostMapping("/changeRole")
    public Result changeUserRole(@RequestBody UserInfo userInfo){
        return userService.changeRole(userInfo);
    }

    @ValidAdminToken
    @PostMapping("/delUser")
    public Result delUser(@RequestBody User user){
        return userService.delUser(user);
    }
}
