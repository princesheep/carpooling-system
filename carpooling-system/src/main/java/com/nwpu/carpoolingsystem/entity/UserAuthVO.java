package com.nwpu.carpoolingsystem.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nwpu.carpoolingsystem.util.CustomAuthorityDeserializer;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Getter
public class UserAuthVO {
    private String username;
    private List<SimpleGrantedAuthority> authorities;

    @JsonDeserialize(using = CustomAuthorityDeserializer.class)
    public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
