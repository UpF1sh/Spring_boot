package com.upf1sh.shop.controller;
//import com.upf1sh.shop.ShopApplication;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.upf1sh.shop.bean.Brand;
import com.upf1sh.shop.bean.User;
import com.upf1sh.shop.mapper.UserMapper;
import com.upf1sh.shop.utils.EmailCheck;
import com.upf1sh.shop.utils.JwtUtils;
import com.upf1sh.shop.utils.L_Code;
import com.upf1sh.shop.utils.Result;
import org.apache.commons.mail.EmailException;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserMapper userMapper;
    //    private EmailCheck emailCheck;
    private String code = "1";

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @PostMapping("/login")
    // querystring: username=zhangsan&password=123   User user,String username,String password
    // json: {username:zhangsan,password:123}
    // 如果前端传递的数据是json格式，必须使用对象接收，同时需要添加@RequestBody
    public Result login(@RequestBody User user) {

        User dbuser = userMapper.findByUN(user.getUsername());
        if (dbuser != null) {

            String pwd = dbuser.getPassword();
            if (pwd.equals(user.getPassword())) {
                String token = JwtUtils.generateToken(user.getUsername());
                L_Code.getMap().put(dbuser.getUsername(), token);
                return Result.ok().data("token", token);
            } else return Result.error().data("error", "密码错误！");
        }
        return Result.error().data("error", "请先注册！");
    }

    @PostMapping("/code")
    public void send(@RequestBody User user) throws EmailException {
        code = Integer.toString((int) (Math.random() * 1000000));
        EmailCheck e = new EmailCheck();
        e.emailsend(user.getEmail(), code);
        this.setCode(code);
        setCode(code);
        System.out.println("成功" + code);
//        User dbuser = userMapper.findByUN(user.getUsername());
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        System.out.println(user);
        User user1 = userMapper.findByUN(user.getUsername());
        if (user1 != null) return Result.error().data("error", "用户已存在!");
//        if (user.getUsername())
        else {
            code = this.getCode();
            System.out.println("if" + code.equals(user.getEmailcode()));
            if (code.equals(user.getEmailcode())) {
                int result = this.userMapper.insert(user);
                if (result == 1) System.out.println("成功");
                return Result.ok();
            } else return Result.error().data("error", "验证码错误！");
        }


    }

    @GetMapping("/info")  // "token:xxx"
    public Result info(String token) {
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        User dbuser = userMapper.findByUN(username);
        String url = dbuser.getIcon();
        return Result.ok().data("name", username).data("avatar", url);
    }

    @GetMapping("/getUserById")
    public Result getUserById(int id) {
        User dbuser = userMapper.selectbyid(id);
        return Result.ok().data("user", dbuser);
    }

    @PostMapping("/getUserByName")
    public Result getUserByName(String name) {
        User dbuser = userMapper.findByUN(name);
        return Result.ok().data("user", dbuser);
    }

    @RequestMapping("/edit")
    public Result edit(User user) {
        // 根据userName修改
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username", user.getUsername());
        int r = userMapper.update(user, updateWrapper);
        if (r == 1)
            return Result.ok();
        return Result.error();
    }

    @PostMapping("/logout")  // "token:xxx"
    public Result logout(String token) {
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        L_Code.getMap().remove(username);
        return Result.ok();
    }

    @GetMapping("/test")
    public int test() {
        return 1;
    }
//
//    @GetMapping("/getu")
//    public Result test2() {
//        return Result.ok().data("user", userMapper.selectList(null));
//    }

    @RequestMapping("/uploadicon")
    public String uploadicon(MultipartFile icon) {
        System.out.println('a');

//      1. 取出文件原始名称
        String originalFilename = icon.getOriginalFilename();
//      2. 为了防止文件名称重复导致覆盖，给每个文件定义一个唯一的名称
        String newFileName = UUID.randomUUID().toString().replace("-", "") + originalFilename;
//      3. 获取程序运行目录
//        String dirPath = System.getProperty("user.dir");
        String dirPath = ("/data");

//      4. 拼接文件存储路径，最终存储到项目的upload目录下
        String path = "/" + "icon" + "/" + newFileName;

        File destFile = new File(dirPath + path);
//        System.out.println("ds" + destFile);
//      5. 如果upload目录不存在则创建
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        try {
//          6. 将前端传来的文件存储到目标路径
            icon.transferTo(destFile);
            // 将相对路径返回给前端，用于显示图片
//          /upload/xxxxxxx.jpg
            return path;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

}
