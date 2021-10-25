package com.dzc.admin.service.impl;

import com.dzc.admin.common.ErrorCode;
import com.dzc.admin.common.Result;
import com.dzc.admin.common.constant.Info;
import com.dzc.admin.common.jwt.JwtUtil;
import com.dzc.admin.dao.UserInfoMapper;
import com.dzc.admin.dao.UserMapper;
import com.dzc.admin.model.User;
import com.dzc.admin.model.UserInfo;
import com.dzc.admin.service.UserService;
import com.dzc.admin.vo.UserInfoVo;
import com.dzc.admin.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: 董政辰
 * @date: 2021/3/3 17:11
 * @description:
 * @email：532587041@qq.com
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

//    @Value("${filepath}")
//    private String serverFilePath;
//    @Autowired UserInfoMapper userInfoMapper;

    @Override
    public Result logIn(User user) {
        //设置用户可以尝试登陆的次数
        final String totaltimes = "10";
        final int againTime = 10;

        String username = user.getUsername();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String loginTimes = (String) valueOperations.get(username);
        if (loginTimes == null) {
            redisTemplate.opsForValue().set(username, totaltimes);
            loginTimes = totaltimes;
        }
        if ("0".equals(loginTimes)) {
            Long expire = redisTemplate.opsForValue().getOperations().getExpire(username);
            return Result.fail("请在" + expire + "秒后再重试，若忘记密码请联系管理员");
        }

        User res = userMapper.selectOneUser(user);

        if (res == null) {
            loginTimes = Integer.parseInt(loginTimes) - 1 + "";
            redisTemplate.opsForValue().set(username, loginTimes, againTime, TimeUnit.MINUTES);
            return Result.fail(ErrorCode.TOKEN_ERROR, "账号或密码错误,还剩" + loginTimes + "次机会");
        } else {
            redisTemplate.opsForValue().set(username, totaltimes);
            String token = JwtUtil.createToken(res);
            return Result.success(new UserVo(token));
        }
    }

    @Override
    public Result getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("user-token");
        int uid = JwtUtil.getTokenInfo(token);
        if (uid == 0)
            return Result.fail(ErrorCode.TOKEN_EXPIRED, "用户登录已过期");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(uid);
        return Result.success(userInfo);
    }


    @Override
    public Result getUserInfoById(Integer id) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        if (userInfo == null) {
            return Result.fail("获取用户信息失败");
        }
        return Result.success(userInfo);
    }

    @Override
    public Result getUser(User user) {
        User res = userMapper.selectByPrimaryKey(user.getId());
        if (res != null) {
            res.setPassword("");
            return Result.success(res);
        } else {
            return Result.fail("获取用户信息失败");
        }
    }

    @Override
    @Transactional
    public Result updateUser(User user) {
        int res = userMapper.updateByPrimaryKeySelective(user);
        if (res != 1)
            return Result.fail("修改密码失败");
        else
            return Result.success("修改密码成功");
    }

    @Override
    @Transactional
    public Result updateUserInfo(UserInfo userInfo) {
        int res = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        if (res != 1)
            return Result.fail("修改个人信息失败");
        else
            return Result.success("修改个人信息成功");
    }

    @Override
    public Result getAllUserInfo() {
        List<UserInfoVo> allUser = userMapper.getAllUser();
        if (allUser.isEmpty())
            return Result.fail("获取所有用户信息失败");
        else
            return Result.success(allUser);
    }

    @Override
    @Transactional
    public Result changeRole(UserInfo userInfo) {
        int res = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        if (res != 1)
            return Result.fail("修改用户权限失败");
        else
            return Result.success("修改用户权限成功");
    }

    @Override
    @Transactional
    public Result signUpOneUser(UserInfoVo userInfoVo) {

        Integer incrementId = userMapper.getIncrementId();

        User addUser = new User();
        addUser.setUsername(userInfoVo.getUsername());
        User user = userMapper.selectUserByUserName(addUser);
        if (user != null) {
            return Result.fail("该账号已存在");
        }

        addUser.setPassword(userInfoVo.getPassword());
        addUser.setId(incrementId);

        UserInfo addInfo = new UserInfo();
        addInfo.setId(incrementId);
        addInfo.setAvatar(Info.DEFAULT_AVATAR);
        addInfo.setRole(Info.DEFAULT_ROLE);
        addInfo.setName(userInfoVo.getName());
        addInfo.setIntroduction("");
        int addUserRes = userMapper.insertSelective(addUser);
        int addInfoRes = userInfoMapper.insertSelective(addInfo);

        if (addUserRes == 1 && addInfoRes == 1) {
            return Result.success("注册成功");
        } else {
            return Result.fail("注册失败,请重新尝试");
        }
    }

    @Override
    @Transactional
    public Result delUser(User user) {
        User delUser = userMapper.selectByPrimaryKey(user.getId());
        // 删除的用户为空或者用户admin不能删除
        if (delUser == null || Info.ADMIN_ROLE.equals(delUser.getUsername()))
            return Result.fail("删除用户失败");
        int res = userMapper.deleteByPrimaryKey(user.getId());

        if (res != 1)
            return Result.fail("删除用户失败");
        else
            return Result.success("删除用户成功");
    }

    @Override
    public Result updateAvtar(MultipartFile uploadFile) {
//        System.out.println(serverFilePath);
        //获取文件名
        String fileName = uploadFile.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = UUID.randomUUID() + suffixName;
        //指定本地文件夹存储图片
//        String filePath = serverFilePath;
//        try {
//            //将图片保存到static文件夹里
//            fileUpload.transferTo(new File(filePath + fileName));
//            return new Massage(0, "success to upload");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new Massage(-1, "fail to upload");
//        }
        return null;
    }
}
