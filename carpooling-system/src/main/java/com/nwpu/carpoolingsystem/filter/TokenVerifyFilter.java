package com.nwpu.carpoolingsystem.filter;

import com.nwpu.carpoolingsystem.config.AuthServerRsaKeyProperties;
import com.nwpu.carpoolingsystem.entity.UserAuthVO;
import com.nwpu.carpoolingsystem.entity.UserToken;
import com.nwpu.carpoolingsystem.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**自定义身份验证器
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/9/3 15:02
 */
public class TokenVerifyFilter extends BasicAuthenticationFilter {

    private AuthServerRsaKeyProperties prop;

    public TokenVerifyFilter(AuthenticationManager authenticationManager, AuthServerRsaKeyProperties prop) {
        super(authenticationManager);
        this.prop = prop;
    }

    /**过滤请求
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-03 15:07:27
     * @param request
     * @param response
     * @param chain
     * @version 1.0
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain chain) throws ServletException, IOException, AuthenticationException, ExpiredJwtException {

        //判断请求体的头中是否包含Authorization
        String authorization = request.getHeader("Authorization");
        //Authorization中是否包含Bearer，不包含直接返回
        if (authorization==null||!authorization.startsWith("Bearer")){
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken token;
        try {
            //解析jwt生成的token，获取权限
            token = getAuthentication(authorization);

        }catch (ExpiredJwtException e){
            // e.printStackTrace();
            chain.doFilter(request, response);
            return;
        }

        //获取后，将Authentication写入SecurityContextHolder中供后序使用
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);


    }



    /**对jwt生成的token进行解析
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-03 15:21:04
     * @param authorization auth
     * @return org.springframework.security.authentication.UsernamePasswordAuthenticationToken
     * @throws
     * @version 1.0
     */
    public UsernamePasswordAuthenticationToken getAuthentication(String authorization) throws ExpiredJwtException{

        if (authorization == null) {
            return null;
        }

        UserToken<UserAuthVO> UserToken;

        //从token中获取有效载荷
        UserToken = JwtUtil.getInfoFromToken(authorization.replace("Bearer", ""), prop.getPublicKey(), UserAuthVO.class);



        //获取当前访问对象
        UserAuthVO userInfo = UserToken.getUserInfo();
        if (userInfo == null){
            return null;
        }

        //将当前访问对象及其权限封装称spring security可识别的token
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userInfo,null,userInfo.getAuthorities());
        return token;
    }
}

