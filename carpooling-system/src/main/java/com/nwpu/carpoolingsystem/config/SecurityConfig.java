package com.nwpu.carpoolingsystem.config;

import com.nwpu.carpoolingsystem.filter.TokenLoginFilter;
import com.nwpu.carpoolingsystem.filter.TokenVerifyFilter;
import com.nwpu.carpoolingsystem.service.impl.RememberMeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;

import java.util.UUID;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private AuthServerRsaKeyProperties properties;

    @Autowired
    private PersistentTokenRepositoryImpl repository;

    @Bean
    public BCryptPasswordEncoder myPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /**
     * 配置自定义过滤器
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //禁用跨域保护，取代它的是jwt
        http.csrf().disable();

//        http.rememberMe().rememberMeParameter("rememberMe").tokenValiditySeconds(3600*24).rememberMeServices(rememberMeServices());

        //允许匿名访问的方法
        http.authorizeRequests().antMatchers("/login", "/register").anonymous()
        //其他需要鉴权
        .anyRequest().authenticated();



        //添加认证过滤器
        http.addFilter(new TokenLoginFilter(authenticationManager(),properties));

        //添加验证过滤器
        http.addFilter(new TokenVerifyFilter(authenticationManager(),properties));

        //添加自定义异常处理
        http.exceptionHandling().authenticationEntryPoint(new MyAuthenticationEntryPoint());
        http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler());


        //禁用session,前后端分离是无状态的
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }



    /**配置密码加密策略
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-03 15:50:46
     * @param authenticationManagerBuilder
     * @version 1.0
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(myPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception{
        //忽略静态资源
        webSecurity.ignoring().antMatchers("/assents/**","/login.html");
    }

//    @Bean
//    public RememberMeServices rememberMeServices() {
//
//        return new RememberMeServiceImpl(UUID.randomUUID().toString(), userService, repository);
//    }

}

