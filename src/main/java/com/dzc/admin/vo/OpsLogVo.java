package com.dzc.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: 董政辰
 * @date: 2021/3/18 11:44
 * @description:
 * @email：532587041@qq.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpsLogVo {

    private Integer id;

    private String name;

    private String uid;

    private String method;

    private String args;

    private Integer status;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT")
    private Date createTime;

}
