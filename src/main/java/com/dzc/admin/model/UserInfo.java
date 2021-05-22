package com.dzc.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfo {
    private Integer id;

    private String role;

    private String name;

    private String avatar;

    private String introduction;

    private String department;
}