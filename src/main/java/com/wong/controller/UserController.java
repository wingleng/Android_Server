package com.wong.controller;

import com.wong.dao.mysql.service.UserService;
import com.wong.service.LoginService;
import com.wong.utils.Log;
import com.wong.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;
    /**
     * 注册接口，将用户添加到数据库当中
     * @param username
     * @param password
     * @return
     */

    @PostMapping("/addUser")
    public String addUser(@RequestParam("username")String username,
                          @RequestParam("password")String password){
        Log.i(username);
        Log.i(password);
      return String.valueOf(userService.AddNewUser(username,password));
    }

    /**
     * 登录接口，验证用户名和密码是否存在或者正确。
     * 如果登录成功，就会使用用户对象的id创建一个token，
     * 顺便将token放进redis中，作为键，将User对象json化，也放进redis中。这样做的目的是为了减少数据库操作，因为可以直接在redis中拿到对象。
     * 最后将token返回到客户端。
     */
    @PostMapping("/login")
    public Result Userlogin(@RequestParam("username")String username,
                            @RequestParam("password")String password){
        return loginService.login(username,password);
    }
}
