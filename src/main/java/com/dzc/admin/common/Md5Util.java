package com.dzc.admin.common;

import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: 董政辰
 * @date: 2021/3/4 15:32
 * @description:
 * @email：532587041@qq.com
 */
public class Md5Util {

    /**
     * 对字符进行hash计算
     * @param text
     * @return 字符串的hash值
     */
    public static String createHash(String text){
        if (StringUtils.isEmpty(text))
            return "";
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(text.getBytes("UTF-8"));
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
