package com.dzc.admin.dao;

import com.dzc.admin.model.User;
import com.dzc.admin.vo.UserInfoVo;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectOneUser(User user);

    List<UserInfoVo> getAllUser();

    User selectUserByUserName(User user);

    Integer getIncrementId();
}