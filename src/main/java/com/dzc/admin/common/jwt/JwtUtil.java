package com.dzc.admin.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dzc.admin.model.User;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 董政辰
 * @date: 2021/3/4 10:36
 * @description:
 * @email：532587041@qq.com
 */
public class JwtUtil {

    /**
     * 自定义 JWT私钥
     */
    private static String SECRET = "admin^&^";

    //过期时间 单位：秒
    private static final int EXPIRE_TIME = 10*60;


    /**
     * 生成token
     */
    public static String createToken(User user) {
        JWTCreator.Builder builder = JWT.create();
        Map<String, Object> map = new HashMap();
        map.put("uid", user.getId());
        map.put("username", user.getUsername());
        map.put("password", user.getPassword());

        map.forEach((k, v) -> {
            builder.withClaim(k, v.toString());
        });

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, EXPIRE_TIME);
        builder.withExpiresAt(instance.getTime());

        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 校验token是否合法
     */
    public static boolean verifyToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        } catch (JWTVerificationException e) {
            return false;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 对token的解密 获取用户ID
     *
     * @Param: 传入token
     */
    public static Integer getTokenInfo(String token) {
        try {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        String res = jwt.getClaim("uid").asString();
        return Integer.valueOf(res);
        } catch (Exception exception) {
            return 0;
        }
    }


}
