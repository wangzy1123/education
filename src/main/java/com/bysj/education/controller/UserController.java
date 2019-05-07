package com.bysj.education.controller;

import com.bysj.common.util.Result;
import com.bysj.education.model.entity.User;
import com.bysj.education.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("/getUserById")
    public Result getUserById(@RequestParam Integer userId){
        if(userId == null){
            return Result.ERROR;
        }
        User user = this.userService.selectById(userId);
        return new Result(user, Result.SUCCESS);
    }

    @PostMapping("/uploadHeadImg")
    public Result uploadHeadImg(@RequestParam MultipartFile file, HttpServletRequest request){
        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String path = "D:\\upload\\headImg\\" + today;
        File saveDir = new File(path);
        if(!saveDir.exists()){
            saveDir.mkdirs();
        }
        String filename = file.getOriginalFilename();
        String filePath = path + "\\" +  filename;
        try {
            InputStream is = file.getInputStream();
            FileOutputStream fos = new FileOutputStream(filePath);
            byte[] buffer = new byte[8192]; // 每次读8K字节
            int count = 0;
            // 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中
            while ((count = is.read(buffer)) > 0) {
                fos.write(buffer, 0, count); // 向服务端文件写入字节流
            }
            fos.close(); // 关闭FileOutputStream对象
            is.close(); // InputStream对象
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR;
        }
        return new Result(filePath, Result.SUCCESS, "上传成功");
    }

}
