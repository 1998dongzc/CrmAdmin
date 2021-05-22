package com.dzc.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginLog {
    private Integer id;

    private Integer uId;

    private Date loginTime;

  }