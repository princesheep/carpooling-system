package com.nwpu.carpoolingsystem.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Object getCurrentUser() {
        Authentication authentication = getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getPrincipal();  // 获取当前用户的信息（通常是实现了 UserDetails 接口的用户对象）
        }
        return null;  // 如果没有认证信息，返回 null
    }
}

