package com.nwpu.carpoolingsystem.controller;

import com.nwpu.carpoolingsystem.entity.Result;
import com.nwpu.carpoolingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzy
 * @since 2024-12-20
 */
@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UserService userService;

    // 用户注册
    @PostMapping("/register")
    public Result registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String email,
                               @RequestParam String phone) {
//        return null;
        return userService.registerUser(username, password, email, phone);
    }

    // 用户登录
//    @PostMapping("/login")
//    public String loginUser(@RequestParam String username,
//                            @RequestParam String password) {
//        return userService.loginUser(username, password);
//    }
}
