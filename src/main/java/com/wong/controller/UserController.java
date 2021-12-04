package com.wong.controller;

import com.wong.dao.mysql.service.UserService;
import com.wong.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestParam("username")String username,
                          @RequestParam("password")String password){
        Log.i(username);
        Log.i(password);
      return String.valueOf(userService.AddNewUser(username,password));
    }


}
