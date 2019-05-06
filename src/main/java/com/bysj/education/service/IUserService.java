package com.bysj.education.service;

import com.baomidou.mybatisplus.service.IService;
import com.bysj.education.model.entity.User;

import java.util.Map;

public interface IUserService extends IService<User> {

    User findByAccount(String userAccount);

    User regist(Map<String, Object> params);

}
