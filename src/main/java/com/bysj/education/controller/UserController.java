package com.bysj.education.controller;

import com.bysj.common.util.Result;
import com.bysj.education.model.entity.User;
import com.bysj.education.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/login")
    public Result login(@RequestParam Map<String, Object> map){
        User loginUser = this.userService.findByAccount(map.get("userAccount").toString());
        if(loginUser == null){
            return new Result(Result.FAIL,"当前账号不存在");
        }else{
            if(loginUser.getPwd().equals(map.get("pwd").toString())){
                return new Result(loginUser, Result.SUCCESS, "登录成功");
            }else{
                return new Result(Result.FAIL,"密码错误");
            }
        }
    }

    @PostMapping("/regist")
    public Result regist(@RequestParam Map<String, Object> map){
        User loginUser = this.userService.findByAccount(map.get("userAccount").toString());
        if(loginUser != null){
            return new Result(Result.FAIL, "所输用户已存在");
        }else{
            User user = userService.regist(map);
            if(user != null){
                return new Result(user, Result.SUCCESS, "注册成功");
            }else{
                return new Result(Result.FAIL, "注册失败,请联系管理员");
            }
        }
    }

}
