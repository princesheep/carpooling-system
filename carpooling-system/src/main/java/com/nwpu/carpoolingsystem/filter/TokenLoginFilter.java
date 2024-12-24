package com.nwpu.carpoolingsystem.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nwpu.carpoolingsystem.config.AuthServerRsaKeyProperties;
import com.nwpu.carpoolingsystem.entity.UserAuthVO;
import com.nwpu.carpoolingsystem.entity.UserLoginVo;
import com.nwpu.carpoolingsystem.util.JwtUtil;
import com.nwpu.carpoolingsystem.util.SecurityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义认证过滤器
 * @author wzy
 * @version 1.0
 * @date 2024/12/24 20:25
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 认证管理器
     */

    private AuthenticationManager authenticationManager;

    private AuthServerRsaKeyProperties prop;

    /**
     * 构造注入
     */
    public TokenLoginFilter(AuthenticationManager authenticationManager, AuthServerRsaKeyProperties prop) {
        this.authenticationManager = authenticationManager;
        this.prop = prop;

    }


    /**
     * 接收并解析用户凭证，并返回json数据
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){

        //判断请求是否为POST,禁用GET请求提交数据
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException(
                    "只支持POST请求方式");
        }


        //将json数据转换为java bean对象
        try {
//            BufferedReader reader = request.getReader();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//            System.out.println(request.getInputStream());
            UserLoginVo user = new ObjectMapper().readValue(request.getInputStream(), UserLoginVo.class);

            if (user.getUsername()==null){
                user.setUsername("");
            }

            if (user.getPassword() == null) {
                user.setPassword("");
            }
            user.getUsername().trim();
            //将用户信息交给spring security做认证操作
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()));
        }catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

    /**
     * 这个方法会在验证成功时被调用
     *用户登录成功后，生成token,并且返回json数据给前端
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult) {
        //获取当前登录对象
        UserAuthVO user = new UserAuthVO();
        user.setUsername(authResult.getName());
        user.setAuthorities((List<SimpleGrantedAuthority>) authResult.getAuthorities());

        //使用jwt创建一个token，私钥加密
        String token = JwtUtil.generateTokenExpireInMinutes(user,prop.getPrivateKey(),15);

        //返回token
        response.addHeader("Authorization","Bearer"+token);

        //登录成功返回json数据提示
        try {
            //生成消息
            Map<String, Object> map = new HashMap<>();
            map.put("code",HttpServletResponse.SC_OK);
            map.put("msg","登录成功");
            //响应数据
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter writer = response.getWriter();
            writer.write(new ObjectMapper().writeValueAsString(map));
            writer.flush();
            writer.close();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}


