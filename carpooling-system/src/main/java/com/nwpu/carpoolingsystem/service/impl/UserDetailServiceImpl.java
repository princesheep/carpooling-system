package com.nwpu.carpoolingsystem.service.impl;

import com.nwpu.carpoolingsystem.entity.User;
import com.nwpu.carpoolingsystem.entity.UserInfo;
import com.nwpu.carpoolingsystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userservice")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null){
            return null;
        }
        User user = userMapper.findByUsername(username);
        List<String> roles = userMapper.findRoleByUserId(user.getId());
        List<SimpleGrantedAuthority> list  = new ArrayList<>();
        for(String role : roles){
            list.add(new SimpleGrantedAuthority(role));
        }
        UserDetails userDetails = new UserInfo(username, user.getPassword(), list);
        return userDetails;
    }
}
