package com.dzc.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Apply {
    private Integer id;

    private Integer roomId;

    private String deviceName;

    private String localName;

    private Integer num;

    @JsonProperty("uId")
    private Integer uId;

    private Date createTime;

    private Integer code;
}