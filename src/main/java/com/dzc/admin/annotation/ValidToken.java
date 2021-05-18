package com.dzc.admin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: 董政辰
 * @date: 2021/3/4 15:59
 * @description: 用于标记 方法是否验证token
 * @email：532587041@qq.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidToken {
    boolean validate() default true;
}
