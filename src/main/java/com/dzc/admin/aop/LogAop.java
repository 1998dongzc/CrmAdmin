package com.dzc.admin.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dzc.admin.common.Result;
import com.dzc.admin.common.jwt.JwtUtil;
import com.dzc.admin.dao.OpsLogMapper;
import com.dzc.admin.model.OpsLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 * @author: 董政辰
 * @date: 2021/3/18 10:22
 * @description:
 * @email：532587041@qq.com
 */
@Aspect
@Component
public class LogAop {

    private OpsLog opsLog;

    @Autowired
    private OpsLogMapper opsLogMapper;

    @Pointcut("execution(public * com.dzc.admin.controller..*.*(..))")//切入点描述 这个是controller包的切入点
    public void controllerLog() {
    }//签名，可以理解成这个切入点的一个名称

    @Before("controllerLog()")
    public void around(JoinPoint jp) {
        try {
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();
            String token = request.getHeader("user-token");
            String methodName = jp.getSignature().getName();
            Integer uid = JwtUtil.getTokenInfo(token);
            if (uid == null || methodName.startsWith("get") || methodName.startsWith("updateUser")) {
                opsLog = null;
                return;
            }

            Object[] obj = jp.getArgs();
            StringBuilder args = new StringBuilder("参数:");
            for (Object t : obj) {
                args.append(t + "|");
            }
            opsLog = new OpsLog();
            opsLog.setArgs(args.toString());
            opsLog.setUid(uid);
            opsLog.setMethod(methodName);
        } catch (Exception exception) {
            opsLog = null;
        }
    }

    @AfterReturning(value = "controllerLog()", returning = "result")
    public void afterReturning(Object result) {
        Result res = (Result) result;
        if (opsLog != null && result != null) {
            int status = res.getStatus() == 200 ? 1 : 0;
            opsLog.setStatus(status);
            int addOpslog = opsLogMapper.insertSelective(opsLog);
            if (addOpslog != 1)
                System.out.println("增加操作日志数据库失败");
        }
    }
}
