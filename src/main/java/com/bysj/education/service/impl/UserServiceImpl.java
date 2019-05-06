package com.bysj.education.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bysj.education.mapper.UserMapper;
import com.bysj.education.model.entity.User;
import com.bysj.education.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findByAccount(String userAccount) {

        User condition = new User();
        condition.setUserAccount(userAccount);
        User user = null;
        try {
            user = userMapper.selectOne(condition);
        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User regist(Map<String, Object> map) {

        User user = new User();
        user.setUserAccount(map.get("userAccount").toString());
        user.setIsAdmin(0);
        user.setState(1);
        user.setPwd(map.get("pwd").toString());
        user.setUserName(map.get("userName").toString());
        try {
            userMapper.insert(user);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return user;
    }
}
