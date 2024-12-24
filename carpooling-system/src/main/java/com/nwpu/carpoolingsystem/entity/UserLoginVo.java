package com.nwpu.carpoolingsystem.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginVo implements Serializable {

    private String username;
    private String password;
    private Boolean rememberMe;

}

