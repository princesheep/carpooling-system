package com.nwpu.carpoolingsystem.service.impl;


import com.nwpu.carpoolingsystem.entity.Result;
import com.nwpu.carpoolingsystem.entity.User;
import com.nwpu.carpoolingsystem.entity.UserRole;
import com.nwpu.carpoolingsystem.mapper.UserMapper;
import com.nwpu.carpoolingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzy
 * @since 2024-12-20
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Result registerUser(String username, String password, String email, String phone) {
        User existingUser = userMapper.findByUsername(username);
        if (existingUser != null) {
            return new Result(500, "用户已存在");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));  // 密码加密
        user.setEmail(email);
        user.setPhone(phone);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        int rows = userMapper.insertUser(user);
        user.setId(userMapper.findIdByUserName(username));
        if (rows > 0) {
            UserRole userRole = new UserRole();
            userRole.setId(user.getId());
            userRole.setRole("ROLE_USER");
            userMapper.insertUserRole(userRole);
        }
        return new Result(200, "注册成功");
    }

//    @Override
//    public String loginUser(String username, String password) {
//        Optional<User> userOptional = userMapper.findByUsername(username);
//        if (!userOptional.isPresent()) {
//            return "用户不存在";
//        }
//
//        User user = userOptional.get();
//        if (passwordEncoder.matches(password, user.getPassword())) {
//            return "登录成功";
//        } else {
//            return "密码错误";
//        }
//    }


}
