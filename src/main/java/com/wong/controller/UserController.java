package com.wong.controller;

import com.wong.dao.mysql.service.UserService;
import com.wong.service.LoginService;
import com.wong.utils.Log;
import com.wong.vo.LoginParam;
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
     * @param loginParam
     * @return
     */

    @PostMapping("/addUser")
    public Result addUser(@RequestBody LoginParam loginParam){
        Log.i(loginParam.getUsername());
        Log.i(loginParam.getPassword());
      return userService.AddNewUser(loginParam.getUsername(),loginParam.getPassword());
    }

    /**
     * 登录接口，验证用户名和密码是否存在或者正确。
     * 如果登录成功，就会使用用户对象的id创建一个token，
     * 顺便将token放进redis中，作为键，将User对象json化，也放进redis中。这样做的目的是为了减少数据库操作，因为可以直接在redis中拿到对象。
     * 最后将token返回到客户端。
     *  @param loginParam
     *  @return
     */
    @PostMapping("/login")
    public Result Userlogin(@RequestBody LoginParam loginParam){
        return loginService.login(loginParam.getUsername(),loginParam.getPassword());
    }


    /**
     * 再添加一个接口，用户登录成功之后，可以直接通过token获取当前用户信息，不用再登录
     */


    @GetMapping("currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token){

        return userService.getUserInfoByToken(token);
    }

}
