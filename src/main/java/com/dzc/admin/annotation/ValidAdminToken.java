package com.dzc.admin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: 董政辰
 * @date: 2021/3/18 20:49
 * @description:
 * @email：532587041@qq.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAdminToken {
    boolean validate() default true;
}
