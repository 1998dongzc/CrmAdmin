package com.dzc.admin.service;

import com.dzc.admin.common.Result;
import com.dzc.admin.model.User;
import com.dzc.admin.model.UserInfo;
import com.dzc.admin.vo.UserInfoVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: 董政辰
 * @date: 2021/3/3 17:10
 * @description:
 * @email：532587041@qq.com
 */
public interface UserService {

    Result logIn(User user);

    Result getUserInfo(HttpServletRequest request);

    Result getUserInfoById(Integer id);

    Result getUser(User user);

    Result updateUser(User user);

    Result updateUserInfo(UserInfo userInfo);

    Result getAllUserInfo();

    Result changeRole(UserInfo userInfo);

    Result signUpOneUser(UserInfoVo userInfoVo);

    Result delUser(User user);

    Result updateAvtar(MultipartFile uploadFile);
}
