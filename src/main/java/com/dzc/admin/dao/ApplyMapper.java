package com.dzc.admin.dao;

import com.dzc.admin.model.Apply;
import com.dzc.admin.vo.ApplyVo;

import java.util.List;

public interface ApplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Apply record);

    int insertSelective(Apply record);

    Apply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Apply record);

    int updateByPrimaryKey(Apply record);

    List<ApplyVo> selectAllApplys();
}