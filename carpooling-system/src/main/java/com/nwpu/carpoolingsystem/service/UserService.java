package com.nwpu.carpoolingsystem.service;

import com.nwpu.carpoolingsystem.entity.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzy
 * @since 2024-12-20
 */
public interface UserService {
    Result registerUser(String username, String password, String email, String phone);
//    String loginUser(String username, String password);
}
