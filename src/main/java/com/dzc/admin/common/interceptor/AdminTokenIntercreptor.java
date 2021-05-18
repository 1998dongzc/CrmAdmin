package com.dzc.admin.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.dzc.admin.annotation.ValidAdminToken;
import com.dzc.admin.common.ErrorCode;
import com.dzc.admin.common.Result;
import com.dzc.admin.common.constant.Info;
import com.dzc.admin.common.jwt.JwtUtil;
import com.dzc.admin.dao.UserInfoMapper;
import com.dzc.admin.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: 董政辰
 * @date: 2021/3/18 20:49
 * @description:
 * @email：532587041@qq.com
 */
@Component
public class AdminTokenIntercreptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        boolean res;

        //此部分用于获取方法上是否有ValidAdminToken或者validate属性值是否为true
        //使用ValidAdminToken默认使用token校验
        ValidAdminToken annotation = null;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(ValidAdminToken.class);
        } else {
            return true;
        }
        //没有声明需要权限,或者声明不验证权限
        if (annotation == null || annotation.validate() == false) {
            return true;
        }


        String token = request.getHeader("user-token");

        // 设置Response返回信息
        Object obj = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        //判断是否token是否为空
        if (StringUtils.isEmpty(token)) {
            JSONObject.toJSON(Result.fail(ErrorCode.TOKEN_EXPIRED, "用户登陆已经过期"));
            res = false;
        } else {
            boolean validToken = JwtUtil.verifyToken(token);

            if (validToken) {
                int uid = JwtUtil.getTokenInfo(token);
                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(uid);
                // 判断权限是否为admin或root
                res = Info.ADMIN_ROLE.equals(userInfo.getRole())||Info.ROOT_ROLE.equals(userInfo.getRole());
                if (!res)
                    obj = JSONObject.toJSON(Result.fail(ErrorCode.TOKEN_ERROR, "用户没有该权限"));
            } else {
                obj = JSONObject.toJSON(Result.fail(ErrorCode.TOKEN_EXPIRED, "用户登陆已经过期"));
                res = false;
            }

        }
        PrintWriter writer = null;

        //反馈给前端 提示token过期
        if (!res) {
            try {
                writer = response.getWriter();
                writer.print(obj);
            } catch (Exception e) {
                System.out.println("token检查出现了异常");
            } finally {
                writer.close();
            }
        }
        return res;
    }

}
