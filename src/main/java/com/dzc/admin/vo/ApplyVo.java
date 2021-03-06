package com.dzc.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author: 董政辰
 * @date: 2021/3/10 21:22
 * @description:
 * @email：532587041@qq.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApplyVo {

    private Integer id;

    private Integer roomId;

    private String deviceName;

    private String localName;

    private Integer num;

    private Integer uid;

    private String name;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT")
    private Date createTime;

    private Integer code;

}
